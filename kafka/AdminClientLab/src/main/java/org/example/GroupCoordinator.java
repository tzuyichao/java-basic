package org.example;

import org.apache.kafka.clients.admin.*;
import org.example.config.EnvConfig;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class GroupCoordinator {
    public static void main(String[] args) {
        final String groupId = "DQA-DFX-Kafka-Account";
        Properties props = EnvConfig.getConfiguration();
        try(AdminClient adminClient = KafkaAdminClient.create(props)) {
            DescribeConsumerGroupsResult result = adminClient.describeConsumerGroups(Collections.singletonList(groupId), new DescribeConsumerGroupsOptions());
            Map<String, ConsumerGroupDescription> descriptions = result.all().get();
            ConsumerGroupDescription description = descriptions.get(groupId);
            System.out.println("Coordinator: " + description.coordinator());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
