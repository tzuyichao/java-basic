package org.example.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class SaslConsumerExample {
    private static final Logger logger = LoggerFactory.getLogger(SaslConsumerExample.class);
    private static volatile boolean keepRunning = true;
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            keepRunning = false;
            logger.info("Shutting down...");
        }));

        String bootstrapServers = "localhost:9092";
        String topic = "test.testtopic.v11";
        String groupId = "Account";
        String account = "Account";
        String password = "qq";

        // Kafka consumer configuration settings
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.put(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, "org.example.consumer.LatencyInterceptor");

        // SASL configuration
        properties.setProperty("security.protocol", "SASL_PLAINTEXT");
        properties.setProperty("sasl.mechanism", "SCRAM-SHA-512");
        properties.put("sasl.jaas.config", String.format("org.apache.kafka.common.security.scram.ScramLoginModule required username='%s' password='%s';", account, password));

        // Create the consumer
        try(KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);) {

            // Subscribe to the topic
            consumer.subscribe(Collections.singletonList(topic));

            System.out.println(consumer.groupMetadata());

            // Poll for new data
            while (keepRunning) {
                ConsumerRecords<String, String> records =
                        consumer.poll(Duration.ofMillis(100));

                for (ConsumerRecord<String, String> record : records){
                    logger.info("Key: " + record.key() + ", Value: " + record.value());
                    logger.info("Partition: " + record.partition() + ", Offset:" + record.offset());
                }
            }
        }
    }
}
