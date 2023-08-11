package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class TopicUnderReplicate {
    private static Logger logger = LoggerFactory.getLogger(TopicUnderReplicate.class);
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
        try (AdminClient adminClient = AdminClient.create(props)) {
            ListTopicsOptions options = new ListTopicsOptions();
            options.listInternal(true);
            ListTopicsResult listTopicsResult = adminClient.listTopics(options);
            Collection<TopicListing> topicListings = listTopicsResult.listings().get();
            Map<String, KafkaFuture<TopicDescription>> topics = adminClient.describeTopics(topicListings.stream().map(tl -> tl.name()).collect(Collectors.toList())).topicNameValues();

            topics.forEach((name, entry) -> {
                try {
                    TopicDescription topicDescription = entry.get();
                    int underReplicatedPartitions = topicDescription.partitions().stream()
                            .mapToInt(partitionInfo -> partitionInfo.replicas().size() - partitionInfo.isr().size())
                            .sum();
                    if(underReplicatedPartitions > 0) {
                        System.out.println(name + ", Under-replicated partitions: " + underReplicatedPartitions);
                    }
                } catch (ExecutionException | InterruptedException e) {
                    logger.error("Error {}", name, e);
                }
            });
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
