package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ConsumerGroupDescription;
import org.apache.kafka.clients.admin.DescribeConsumerGroupsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ConsumerGroupMetadata {
    public static void main(String[] args) {
        String consumerGroupId = args[0];
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
            DescribeConsumerGroupsResult result = adminClient.describeConsumerGroups(Collections.singletonList(consumerGroupId));
            ConsumerGroupDescription desc = result.describedGroups().get(consumerGroupId).get();

            int memberCount = desc.members().size();
            System.out.println("Active members: " + memberCount);

            if (memberCount == 1) {
                System.out.println("Only one consumer in the group (myself).");
            } else {
                desc.members().forEach(member -> {
                    System.out.println("Member ID: " + member.consumerId());
                    System.out.println("Client ID: " + member.clientId());
                    System.out.println("Host: " + member.host());
                    System.out.println("Assignment: " + member.assignment());
                    System.out.println("Group Instance Id: " + member.groupInstanceId());
                    System.out.println("-------------------------");
                });
            }
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
