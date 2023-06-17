package org.example.kafka;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class TopicInfo {

    public static void main(String[] args) throws IOException {
        String topicName = null;
        if(args.length == 2) {
            if(args[0].equals("-topic")) {
                topicName = args[1];
            }
        }

        Properties props = new Properties();
        InputStream fileInputStream = new FileInputStream("client.properties");
        props.load(fileInputStream);
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
