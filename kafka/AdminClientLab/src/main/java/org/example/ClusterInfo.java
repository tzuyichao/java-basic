package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.common.Node;

import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ClusterInfo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Dotenv dotenv = Dotenv.load();
        Properties props = new Properties();
        props.put("bootstrap.servers", dotenv.get("BOOTSTRAP_SERVER"));
        props.put("fetch.max.bytes", 1024);
        props.put("enable.auto.commit", true);
        props.put("auto.commit.interval.ms", 1000);
        // SECURITY_PROTOCOL=SASL_PLAINTEXT
        props.put("security.protocol", dotenv.get("SECURITY_PROTOCOL"));
        // SASL_MECHANISM=SCRAM-SHA-512
        props.put("sasl.mechanism", dotenv.get("SASL_MECHANISM"));
        props.put("auto.offset.reset","earliest");
        //String jaas = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"ACCOUNT\" password=\"PASSWORD\";";
        props.put("sasl.jaas.config", dotenv.get("JAAS"));
        try(AdminClient adminClient = KafkaAdminClient.create(props)) {

            DescribeClusterResult result = adminClient.describeCluster();

            String clusterId = result.clusterId().get();
            System.out.println("Kafka cluster id: " + clusterId);

            Collection<Node> nodes = result.nodes().get();

            for (Node node : nodes) {
                System.out.println("Kafka broker node: " + node);
            }

            System.out.println("-------");

            Node controllerNode = result.controller().get();

            System.out.println("Kafka controller node: " + controllerNode);
            System.out.println("id: " + controllerNode.id() + ", host: " + controllerNode.host() + ", port: " + controllerNode.port() + ", rack: " + controllerNode.rack());
        }
    }
}
