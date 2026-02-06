package org.example.network;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.common.Node;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class NetworkLab {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        Properties props = new Properties();
        props.put("bootstrap.servers", dotenv.get("BOOTSTRAP_SERVER"));
        props.put("security.protocol", dotenv.get("SECURITY_PROTOCOL"));
        props.put("sasl.mechanism", dotenv.get("SASL_MECHANISM"));
        props.put("sasl.jaas.config", dotenv.get("JAAS"));

        try(AdminClient adminClient = KafkaAdminClient.create(props)) {
            DescribeClusterResult result = adminClient.describeCluster();
            String clusterId = result.clusterId().get();
            System.out.println("Kafka cluster id: " + clusterId);

            Collection<Node> nodes = result.nodes().get();

            for (Node node : nodes) {
                System.out.printf("nodeId=%d host=%s port=%d rack=%s%n",
                        node.id(), node.host(), node.port(), node.rack());
            }
            int connectTimeoutMs = 3000;
            int parallel = 6;
            boolean resolve = true;
            List<Result> results = probeAll(nodes, connectTimeoutMs, parallel, resolve);
            System.out.println(results);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static class Result {
        final Node node;
        final boolean ok;
        final long rttMs;
        final String resolvedIps;   // optional
        final String error;

        Result(Node node, boolean ok, long rttMs, String resolvedIps, String error) {
            this.node = node;
            this.ok = ok;
            this.rttMs = rttMs;
            this.resolvedIps = resolvedIps;
            this.error = error;
        }
    }


    private static List<Result> probeAll(Collection<Node> nodes, int connectTimeoutMs, int parallel, boolean resolve) throws InterruptedException{
        ExecutorService pool = Executors.newFixedThreadPool(Math.max(1, parallel));
        try {
            List<Callable<Result>> tasks = new ArrayList<>();
            for (Node n : nodes) {
                tasks.add(() -> probeOne(n, connectTimeoutMs, resolve));
            }
            List<Future<Result>> futures = pool.invokeAll(tasks);

            List<Result> results = new ArrayList<>(futures.size());
            for (Future<Result> f : futures) {
                try {
                    results.add(f.get());
                } catch (ExecutionException e) {
                    Throwable c = e.getCause() != null ? e.getCause() : e;
                    // Should be rare; keep it visible
                    results.add(new Result(new Node(-1, "unknown", -1), false, -1, "", c.toString()));
                }
            }
            // keep stable order by nodeId
            results.sort(Comparator.comparingInt(r -> r.node.id()));
            return results;
        } finally {
            pool.shutdownNow();
        }
    }

    private static Result probeOne(Node node, int connectTimeoutMs, boolean resolve) {
        String resolved = "";
        try {
            if (resolve) {
                InetAddress[] addrs = InetAddress.getAllByName(node.host());
                resolved = Arrays.stream(addrs).map(InetAddress::getHostAddress).collect(Collectors.joining(","));
            }
            long startTime = System.nanoTime();
            try (Socket s = new Socket()) {
                s.connect(new InetSocketAddress(node.host(), node.port()), connectTimeoutMs);
            }
            long end = System.nanoTime();
            long rttMs = TimeUnit.NANOSECONDS.toMillis(end - startTime);
            return new Result(node, true, rttMs, resolved, "");
        } catch (Exception e) {
            return new Result(node, false, -1, resolved, e.getClass().getSimpleName() + ": " + e.getMessage());
        }
    }
}
