package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.TopicPartitionInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class TopicInfo {
    static Logger logger = LoggerFactory.getLogger(TopicInfo.class);
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
            ListTopicsOptions listTopicsOptions = new ListTopicsOptions();
            listTopicsOptions.listInternal(true);
            ListTopicsResult listTopicsResult = adminClient.listTopics(listTopicsOptions);
            logger.info("Topic Count: {}", listTopicsResult.names().get().size());
            Collection<TopicListing> topicListings = listTopicsResult.listings().get();
            for(TopicListing topicListing : topicListings) {
                System.out.println("Topic ID: " + topicListing.topicId() + ", Topic Name: " + topicListing.name());
                logger.debug("Topic ID: {}, Topic Name: {}", topicListing.topicId(), topicListing.name());
            }
            Map<String, KafkaFuture<TopicDescription>> topicDescriptionMap = adminClient.describeTopics(topicListings.stream().map(it -> it.name()).toList()).topicNameValues();
            topicDescriptionMap.values().stream().forEach(topicDescriptionKafkaFuture -> {
                try {
                    TopicDescription topicDescription = topicDescriptionKafkaFuture.get();
                    String topicName = topicDescription.name();
                    for(TopicPartitionInfo topicPartitionInfo: topicDescription.partitions()) {
                        System.out.println(topicName + ", Partition: " + topicPartitionInfo.partition() + ", Leader: " + topicPartitionInfo.leader().id() + ", replicas: " + topicPartitionInfo.replicas().stream().map(node -> String.valueOf(node.id())).collect(Collectors.joining(";")));
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
