package org.example.topic;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.Config;
import org.apache.kafka.clients.admin.ConfigEntry;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.TopicPartitionInfo;
import org.apache.kafka.common.config.ConfigResource;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;

public class DescribeTopicStatus {
    public static void main(String[] args) {
        if(args.length != 1) {
            System.out.println("Usage: DescribeTopicStatus <topic>");
            System.exit(1);
        }
        String topic = args[0];
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
        try (AdminClient adminClient = AdminClient.create(props)) {
            Map<String, TopicDescription> topicDescriptionMap = adminClient.describeTopics(java.util.Collections.singletonList(topic)).all().get();
            TopicDescription description = topicDescriptionMap.get(topic);
            System.out.println("== Topic Information: " + topic);
            for(TopicPartitionInfo partitionInfo : description.partitions()) {
                System.out.printf("  - Partition ID: %d | Leader: %d | ISR: %s%n",
                        partitionInfo.partition(),
                        partitionInfo.leader().id(),
                        partitionInfo.isr());
                for(Node node :partitionInfo.isr()) {
                    System.out.printf("    - Node ID: %d | Host: %s | Port: %d%n",
                            node.id(),
                            node.host(),
                            node.port());
                }
            }
            ConfigResource clientResource = new ConfigResource(ConfigResource.Type.TOPIC, topic);
            Map<ConfigResource, Config> configs = adminClient.describeConfigs(Collections.singletonList(clientResource)).all().get();

            Config clientConfig = configs.get(clientResource);

            System.out.println("== Topic Configuration:");
            for (ConfigEntry entry : clientConfig.entries()) {
                System.out.printf("  - %s: %s%n", entry.name(), entry.value());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
