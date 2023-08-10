package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewPartitionReassignment;
import org.apache.kafka.common.TopicPartition;

import java.util.*;

public class IncreasingTopicReplicationFactory {
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
        AdminClient adminClient = AdminClient.create(props);
        Map<TopicPartition, Optional<NewPartitionReassignment>> reassignments = new HashMap<>();
        List<Integer> newReplicas = Arrays.asList(1, 4, 7); // Replace with the ids of the new replicas.
        reassignments.put(new TopicPartition("twtpesqlp0.delta.corp", 0), Optional.of(new NewPartitionReassignment(newReplicas)));
        adminClient.alterPartitionReassignments(reassignments);
        adminClient.close();
    }
}
