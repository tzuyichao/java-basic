package org.example.topic;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;

public class DescribeTopicsOffsets {
    static Logger logger = LoggerFactory.getLogger(DescribeTopicsOffsets.class);

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
            listTopicsResult.names().get().forEach(topic -> {
                adminClient.describeTopics(List.of(topic)).topicNameValues().forEach((name, entry) -> {
                    entry.whenComplete((topicDescription, throwable) -> {
                        if (throwable != null) {
                            logger.error("Error getting topic description: ", throwable);
                            return;
                        }
                        topicDescription.partitions().forEach(partitionInfo -> {
                            logger.info("Topic: {}, Partition: {}, Leader: {}, Replicas: {}, ISR: {}", topicDescription.name(), partitionInfo.partition(), partitionInfo.leader().id(), partitionInfo.replicas(), partitionInfo.isr());
                        });
                    });
                });
            });
        } catch (Exception e) {
            logger.error("Error listing topics: ", e);
        }
    }
}
