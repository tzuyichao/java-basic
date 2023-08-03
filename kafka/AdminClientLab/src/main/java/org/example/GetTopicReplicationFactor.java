package org.example;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.DescribeTopicsResult;
import org.apache.kafka.clients.admin.TopicDescription;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class GetTopicReplicationFactor {
    public static void main(String[] args) {
        // Kafka bootstrap servers
        String bootstrapServers = "localhost:9093;localhost:9094";
        // Topic name
        String topic = "replication.test.v0";

        // Kafka AdminClient configuration
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        try (AdminClient adminClient = AdminClient.create(props)) {
            DescribeTopicsResult describeTopicsResult = adminClient.describeTopics(Collections.singletonList(topic));
            TopicDescription topicDescription = describeTopicsResult.topicNameValues().get(topic).get();
            int replicationFactor = topicDescription.partitions().get(0).replicas().size();
            System.out.println("Replication factor of topic " + topic + ": " + replicationFactor);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
