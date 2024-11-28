package org.example.topic;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class OfflinePartition {
    static Logger logger = LoggerFactory.getLogger(OfflinePartition.class);
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
        props.put("auto.offset.reset", "earliest");
        //String jaas = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"ACCOUNT\" password=\"PASSWORD\";";
        props.put("sasl.jaas.config", dotenv.get("JAAS"));
        try (AdminClient adminClient = KafkaAdminClient.create(props)) {
            DescribeClusterResult clusterResult = adminClient.describeCluster();
            String clusterId = clusterResult.clusterId().get();
            System.out.println("Cluster ID: " + clusterId);

            ListTopicsOptions listTopicsOptions = new ListTopicsOptions().listInternal(true);
            Set<String> topics = adminClient.listTopics(listTopicsOptions).names().get();
            DescribeTopicsResult topicDescriptions = adminClient.describeTopics(topics);

            for (Map.Entry<String, TopicDescription> entry : topicDescriptions.all().get().entrySet()) {
                String topic = entry.getKey();
                TopicDescription description = entry.getValue();
                description.partitions().forEach(partition -> {
                    if (partition.isr().isEmpty()) {
                        System.out.printf("Topic: %s, Partition: %d is offline%n",
                                topic, partition.partition());
                    }
                });
            }
        } catch (Exception e) {
            logger.error("Error increasing partitions: ", e);
        }
    }
}
