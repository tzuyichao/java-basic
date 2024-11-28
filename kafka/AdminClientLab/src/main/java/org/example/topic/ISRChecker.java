package org.example.topic;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.common.TopicPartitionInfo;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class ISRChecker {

    public static void main(String[] args) {
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
        props.put("auto.offset.reset", "earliest");
        //String jaas = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"ACCOUNT\" password=\"PASSWORD\";";
        props.put("sasl.jaas.config", dotenv.get("JAAS"));
        try (AdminClient adminClient = AdminClient.create(props)) {
            Set<String> topics = adminClient.listTopics().names().get();
            DescribeTopicsResult topicDescriptions = adminClient.describeTopics(topics);

            for (Map.Entry<String, TopicDescription> entry : topicDescriptions.all().get().entrySet()) {
                String topic = entry.getKey();
                for (TopicPartitionInfo partitionInfo : entry.getValue().partitions()) {
                    List<Integer> replicas = new ArrayList<>(partitionInfo.replicas().stream().map(node -> node.id()).toList());
                    List<Integer> isr = partitionInfo.isr().stream().map(node -> node.id()).toList();

                    replicas.removeAll(isr);
                    if (!replicas.isEmpty()) {
                        System.out.printf("Topic: %s, Partition: %d, Problematic Brokers: %s%n",
                                topic, partitionInfo.partition(), replicas);
                    }
                }
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
