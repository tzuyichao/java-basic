package org.example;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.admin.TopicListing;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class CreateTopicWithReplicationFactor {

    public static void main(String[] args) {
        // Kafka bootstrap servers
        String bootstrapServers = "localhost:9093;localhost:9094";
        // Topic name and replication factor
        String topicName = "replication.test.v0";
        int replicationFactor = 1;

        // Kafka AdminClient configuration
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);

        try (AdminClient adminClient = AdminClient.create(props)) {
            // Create the topic with the desired replication factor
            NewTopic newTopic = new NewTopic(topicName, 1, (short) replicationFactor);
            adminClient.createTopics(Collections.singleton(newTopic)).all().get();
            System.out.println("Topic " + topicName + " created with replication factor " + replicationFactor);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}