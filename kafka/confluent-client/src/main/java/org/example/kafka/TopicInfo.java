package org.example.kafka;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.TopicPartitionInfo;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class TopicInfo {

    public static void main(String[] args) {
        String topicName = null;
        if(args.length == 2) {
            if(args[0].equals("-topic")) {
                topicName = args[1];
            }
        }

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
            if(null == topicName) {
                ListTopicsOptions options = new ListTopicsOptions();
                options.listInternal(true);
                ListTopicsResult result = adminClient.listTopics(options);
                KafkaFuture<Collection<TopicListing>> topicListFuture = result.listings();
                Collection<TopicListing> topicListings = topicListFuture.get();
                describeTopics(adminClient, topicListings.stream().map(topicListing -> topicListing.name()).toList());
            } else {
                describeTopics(adminClient, Arrays.asList(topicName));
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void describeTopics(AdminClient adminClient, Collection<String> topics) {
        DescribeTopicsResult describeTopicsResult = adminClient.describeTopics(topics);
        describeTopicsResult.values().values().stream().forEach(topicDescriptionKafkaFuture -> {
            try {
                TopicDescription topicDescription = topicDescriptionKafkaFuture.get();
                System.out.println(topicDescription.name() + ", Topic Id: " + topicDescription.topicId());
                topicDescription.partitions().stream().forEach(topicPartitionInfo -> {
                    System.out.println("\tPartition: " + topicPartitionInfo.partition() + ", replicas: " + topicPartitionInfo.replicas() + ", leader: " + topicPartitionInfo.leader());
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }

        });
    }
}
