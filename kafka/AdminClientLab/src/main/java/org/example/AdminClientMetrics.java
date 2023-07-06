package org.example;


import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.common.Metric;
import org.apache.kafka.common.MetricName;
import org.example.config.EnvConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Properties;

public class AdminClientMetrics {
    static Logger logger = LoggerFactory.getLogger(AdminClientMetrics.class);
    public static void main(String[] args) {
        Properties props = EnvConfig.getConfiguration();
        try(AdminClient adminClient = KafkaAdminClient.create(props)) {
            Map<MetricName, ? extends Metric> metrics = adminClient.metrics();
            for(MetricName metricName : metrics.keySet()) {
                logger.info("Name: {}", metricName.name());
                logger.info("Description: {}", metricName.description());
                logger.info("Group: {}", metricName.group());
                logger.info("Tags: {}", metricName.tags());

                Metric metric = metrics.get(metricName);
                logger.info("Value: {}", metric.metricValue());
            }
        }
    }
}
