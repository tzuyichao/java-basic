package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ConsumerGroupDescription;
import org.apache.kafka.clients.admin.DescribeConsumerGroupsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

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
            String consumerGroupId = "MES-Kafka-Account";

            DescribeConsumerGroupsResult describeConsumerGroupsResult = adminClient.describeConsumerGroups(Collections.singletonList(consumerGroupId));

            ConsumerGroupDescription consumerGroupDescription = describeConsumerGroupsResult.describedGroups().get(consumerGroupId).get();

            System.out.println("Consumer Group ID: " + consumerGroupDescription.groupId());
            System.out.println("Consumer Group State: " + consumerGroupDescription.state());

            consumerGroupDescription.members().forEach(member -> {
                System.out.println("Member ID: " + member.consumerId());
                System.out.println("Client ID: " + member.clientId());
                System.out.println("Client Host: " + member.host());
                System.out.println("Assigned Partitions: " + member.assignment().topicPartitions());
            });
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
