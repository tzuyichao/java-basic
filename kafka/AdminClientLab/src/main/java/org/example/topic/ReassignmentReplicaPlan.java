package org.example.topic;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AlterPartitionReassignmentsResult;
import org.apache.kafka.clients.admin.NewPartitionReassignment;
import org.apache.kafka.common.TopicPartition;
import org.example.config.EnvConfig;

import java.util.*;

public class ReassignmentReplicaPlan {
    public static void main(String[] args) {
        Properties props = EnvConfig.getConfiguration();

        try (AdminClient adminClient = AdminClient.create(props)) {
            String topicName = "it.mfg.schedule.job-notify.test";

            Map<TopicPartition, Optional<NewPartitionReassignment>> reassignments = new HashMap<>();
            reassignments.put(new TopicPartition(topicName, 0), Optional.of(new NewPartitionReassignment(Arrays.asList(4, 6, 2))));

            AlterPartitionReassignmentsResult result = adminClient.alterPartitionReassignments(reassignments);

            System.out.println(result.all().get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
