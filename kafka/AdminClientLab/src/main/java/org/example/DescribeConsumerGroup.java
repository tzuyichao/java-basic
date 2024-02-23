package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class DescribeConsumerGroup {
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
        props.put("auto.offset.reset","earliest");
        //String jaas = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"ACCOUNT\" password=\"PASSWORD\";";
        props.put("sasl.jaas.config", dotenv.get("JAAS"));
        try(AdminClient adminClient = KafkaAdminClient.create(props)) {
            String consumerGroupId = "NPE-Kafka-Account";

            DescribeConsumerGroupsResult describeConsumerGroupsResult = adminClient.describeConsumerGroups(Collections.singletonList(consumerGroupId));

            ConsumerGroupDescription consumerGroupDescription = describeConsumerGroupsResult.describedGroups().get(consumerGroupId).get();

            System.out.println("Consumer Group ID: " + consumerGroupDescription.groupId());
            System.out.println("Consumer Group State: " + consumerGroupDescription.state());
            System.out.println("Consumer Group Coordinator: " + consumerGroupDescription.coordinator());

            consumerGroupDescription.members().forEach(member -> {
                System.out.println("Member ID: " + member.consumerId());
                System.out.println("Client ID: " + member.clientId());
                System.out.println("Client Host: " + member.host());
                System.out.println("Assigned Partitions: " + member.assignment().topicPartitions());
            });

            ListConsumerGroupOffsetsResult offsetsResult = adminClient.listConsumerGroupOffsets(consumerGroupId);
            Map<TopicPartition, OffsetAndMetadata> offsets = offsetsResult.partitionsToOffsetAndMetadata().get();
            offsets.forEach((tp, offset) -> System.out.println(tp + " -> " + offset));

            Map<TopicPartition, OffsetSpec> topicPartitionOffsetSpecMap = offsets.keySet().stream()
                    .collect(Collectors.toMap(tp -> tp, tp -> OffsetSpec.latest()));
            Map<TopicPartition, ListOffsetsResult.ListOffsetsResultInfo> endOffsets = adminClient.listOffsets(topicPartitionOffsetSpecMap).all().get();

            Map<TopicPartition, OffsetSpec> topicPartitionEarliestOffsetSpecMap = offsets.keySet().stream()
                    .collect(Collectors.toMap(tp -> tp, tp -> OffsetSpec.earliest()));
            Map<TopicPartition, ListOffsetsResult.ListOffsetsResultInfo> earliestOffsets = adminClient.listOffsets(topicPartitionEarliestOffsetSpecMap).all().get();


            offsets.forEach((topicPartition, offsetAndMetadata) -> {
                long endOffset = endOffsets.get(topicPartition).offset();
                long earliestOffset = earliestOffsets.get(topicPartition).offset();
                long lag = endOffset - offsetAndMetadata.offset();
                System.out.println("Partition: " + topicPartition + ", Consumer Group Current Offset: " + offsetAndMetadata.offset() + ", Earliest Offset: " + earliestOffset + ", End Offset: " + endOffset + ", Lag: " + lag);
            });
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
