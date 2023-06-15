package org.example.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.apache.kafka.common.Node;

import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ClusterInfo {
    public static void main(String[] args) {
        Collection<String> servers = Arrays.asList("server1:9093", "server2:9093");
        for(var server : servers) {
            System.out.println("Server: " + server);
            Properties props = new Properties();
            props.put("bootstrap.servers", server);
            props.put("fetch.max.bytes", 1024);
            props.put("enable.auto.commit", true);
            props.put("auto.commit.interval.ms", 1000);
            props.put("security.protocol", "SASL_PLAINTEXT");
            props.put("sasl.mechanism", "SCRAM-SHA-512");
            props.put("auto.offset.reset", "earliest");
            String jaas = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"ACCOUNT\" password=\"PASSWORD\";";
            props.put("sasl.jaas.config", jaas);
            try (AdminClient adminClient = AdminClient.create(props)) {
                DescribeClusterResult result = adminClient.describeCluster();

                String clusterId = result.clusterId().get();
                System.out.println("Kafka cluster id: " + clusterId);

                Collection<Node> nodes = result.nodes().get();
                System.out.println("Available nodes: " + nodes.size());
                for (Node node : nodes) {
                    System.out.println("Kafka broker node: " + node);
                }

                System.out.println("-------");

                Node controllerNode = result.controller().get();

                System.out.println("Kafka controller node: " + controllerNode);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println();
        }
    }
}