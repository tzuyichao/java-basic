package org.example.kafka;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.*;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class ReplicaReassignment {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "server:9093");
        props.put("fetch.max.bytes", 1024);
        props.put("enable.auto.commit", true);
        props.put("auto.commit.interval.ms", 1000);
        props.put("security.protocol", "SASL_PLAINTEXT");
        props.put("sasl.mechanism", "SCRAM-SHA-512");
        props.put("auto.offset.reset", "earliest");
        String jaas = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"ACCOUNT\" password=\"PASSWORD\";";
        props.put("sasl.jaas.config", jaas);
        try (AdminClient adminClient = AdminClient.create(props)) {
            ListTopicsOptions options = new ListTopicsOptions();
            options.listInternal(true);
            ListTopicsResult result = adminClient.listTopics(options);
            KafkaFuture<Collection<TopicListing>> topicListFuture = result.listings();
            Collection<TopicListing> topicListings = topicListFuture.get();
            for(var topicListing: topicListings) {
                String topicName = topicListing.name();
                TopicDescription topicDescription = adminClient.describeTopics(List.of(topicName)).values().get(topicName).get();
                List<TopicPartitionInfo> topicPartitions = topicDescription.partitions();
                reassignmentReplicas(adminClient, topicName, topicPartitions);
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void reassignmentReplicas(AdminClient adminClient, String topicName, List<TopicPartitionInfo> topicPartitions) throws InterruptedException, ExecutionException {
        if(topicPartitions.get(0).replicas().size() == 1) {
            List<Integer> replicas = new ArrayList<>();
            replicas.add(2);
            replicas.add(4);
            replicas.add(6);
            for(TopicPartitionInfo topicPartitionInfo : topicPartitions) {
                Collections.rotate(replicas, 1);
                AlterPartitionReassignmentsOptions alterPartitionReassignmentsOptions = new AlterPartitionReassignmentsOptions();
                Map<TopicPartition, Optional<NewPartitionReassignment>> reassignment = new HashMap<>();
                NewPartitionReassignment newPartitionReassignment = new NewPartitionReassignment(replicas);
                reassignment.put(new TopicPartition(topicName, topicPartitionInfo.partition()), Optional.of(newPartitionReassignment));
                AlterPartitionReassignmentsResult alterPartitionReassignmentsResult = adminClient.alterPartitionReassignments(reassignment, alterPartitionReassignmentsOptions);
                alterPartitionReassignmentsResult.all().get();
                System.out.println("Topic: " + topicName + ", Partition: " + topicPartitionInfo.partition() + " reassignment replicas completed.");
            }
        }
    }
}
