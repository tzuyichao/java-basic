package org.example.topic;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.config.ConfigResource;
import org.apache.kafka.common.config.TopicConfig;
import org.example.config.EnvConfig;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class RetentionConfig {
    public static void main(String[] args) {
        Properties props = EnvConfig.getConfiguration();

        try (AdminClient adminClient = AdminClient.create(props)) {
            var topics = adminClient.listTopics().names().get();

            for (String topic : topics) {
                ConfigResource resource = new ConfigResource(ConfigResource.Type.TOPIC, topic);
                DescribeConfigsResult describeConfigsResult = adminClient.describeConfigs(Collections.singletonList(resource));
                Config config = describeConfigsResult.all().get().get(resource);

                String retentionStr = config.get(TopicConfig.RETENTION_MS_CONFIG).value();
                long currentRetention = retentionStr != null ? Long.parseLong(retentionStr) : 0;

                if (currentRetention == -1) {
                    System.out.println("===> " + topic);
                    continue;
                }
                if (currentRetention < 604800000) {
                    Map<ConfigResource, Collection<AlterConfigOp>> updateConfigs = new HashMap<>();
                    ConfigEntry retentionEntry = new ConfigEntry(TopicConfig.RETENTION_MS_CONFIG, String.valueOf(604800000));
                    AlterConfigOp op = new AlterConfigOp(retentionEntry, AlterConfigOp.OpType.SET);
                    updateConfigs.put(resource, Collections.singletonList(op));

                    adminClient.incrementalAlterConfigs(updateConfigs).all().get();
                    System.out.println("Updated retention for topic: " + topic);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
