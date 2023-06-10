package org.example;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewPartitionReassignment;
import org.apache.kafka.common.TopicPartition;

import java.util.*;

public class IncreasingTopicReplicationFactory {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093;localhost:9094");
        AdminClient adminClient = AdminClient.create(props);
        Map<TopicPartition, Optional<NewPartitionReassignment>> reassignments = new HashMap<>();
        List<Integer> newReplicas = Arrays.asList(1, 2); // Replace with the ids of the new replicas.
        reassignments.put(new TopicPartition("replication.test.v0", 0), Optional.of(new NewPartitionReassignment(newReplicas)));
        adminClient.alterPartitionReassignments(reassignments);
        adminClient.close();
    }
}
