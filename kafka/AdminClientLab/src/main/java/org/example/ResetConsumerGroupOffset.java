package org.example;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.TopicListing;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.TopicPartitionInfo;
import org.example.config.EnvConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ResetConsumerGroupOffset {
    public static void main(String[] args) {
        Properties props = EnvConfig.getConfiguration();
        try(AdminClient adminClient = KafkaAdminClient.create(props)) {
            String consumerGroup = "PFMEA-Kafka-Account";
            String topic = "test.testopic.v0";

            Map<String, TopicListing> topics = adminClient.listTopics(new ListTopicsOptions().listInternal(false)).namesToListings().get();
            List<TopicPartitionInfo> partitions = adminClient.describeTopics(List.of(topic)).all().get().get(topic).partitions();

            Map<TopicPartition, OffsetAndMetadata> request = new HashMap<>();
            for(TopicPartitionInfo partition : partitions) {
                request.put(new TopicPartition(topic, partition.partition()), new OffsetAndMetadata(0));
            }

            adminClient.alterConsumerGroupOffsets(consumerGroup, request).all().get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
