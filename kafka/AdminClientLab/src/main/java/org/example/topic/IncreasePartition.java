package org.example.topic;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewPartitions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;

public class IncreasePartition {
    static Logger logger = LoggerFactory.getLogger(IncreasePartition.class);

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
            String topicName = "test";
            int newPartitionCount = 3;
            Map<String, NewPartitions> newPartitions = Collections.singletonMap(topicName, NewPartitions.increaseTo(newPartitionCount));
            adminClient.createPartitions(newPartitions).all().get();
        } catch (Exception e) {
            logger.error("Error increasing partitions: ", e);
        }
    }
}
