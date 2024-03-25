package org.example.topic;

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

public class PreferredReplicaImbalance {
    static Logger logger = LoggerFactory.getLogger(PreferredReplicaImbalance.class);
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
            ListTopicsOptions options = new ListTopicsOptions();
            options.listInternal(true);
            ListTopicsResult listTopicsResult = adminClient.listTopics(options);
            Collection<TopicListing> topicListings = listTopicsResult.listings().get();
            Map<String, KafkaFuture<TopicDescription>> topics = adminClient.describeTopics(topicListings.stream().map(tl -> tl.name()).collect(Collectors.toList())).topicNameValues();

            topics.forEach((name, entry) -> {
                try {
                    TopicDescription topicDescription = entry.get();
                    for (TopicPartitionInfo partitionInfo : topicDescription.partitions()) {
                        int preferredReplica = partitionInfo.replicas().get(0).id();
                        int currentLeader = partitionInfo.leader().id();
                        if (partitionInfo.leader().id() != preferredReplica) {
                            System.out.println("Topic: " + name + ", Partition: " + partitionInfo.partition() + ", Preferred Replica: " + preferredReplica + ", leader: " + currentLeader);
                        } else {
                            logger.info("Topic {}, Partition {} is ok", name, partitionInfo.partition());
                        }
                    }
                } catch (ExecutionException | InterruptedException e) {
                    logger.error("Error {}", name, e);
                }
            });
        } catch (ExecutionException | InterruptedException e) {
            logger.error("listing topics: ", e);
        }
    }
}
