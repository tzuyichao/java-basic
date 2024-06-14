package org.example.consumer;

import io.github.cdimascio.dotenv.Dotenv;
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

public class SaslConsumerExample2 {
    private static final Logger logger = LoggerFactory.getLogger(SaslConsumerExample2.class);
    public static void main(String[] args) {
        String topic = "product.project-management.project.project-change.v0";
        String account = "PFMEA-Kafka-Account";
        String pwd = "1111";
        Dotenv dotenv = Dotenv.load();
        Properties props = new Properties();
        props.put("bootstrap.servers", dotenv.get("BOOTSTRAP_SERVER"));
        // SECURITY_PROTOCOL=SASL_PLAINTEXT
        props.put("security.protocol", dotenv.get("SECURITY_PROTOCOL"));
        // SASL_MECHANISM=SCRAM-SHA-512
        props.put("sasl.mechanism", dotenv.get("SASL_MECHANISM"));
        props.put("auto.offset.reset","earliest");
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, account);
        //props.put("sasl.jaas.config", dotenv.get("JAAS"));
        String jaas = String.format("org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";", account, pwd);
        props.put("sasl.jaas.config", jaas);

        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // Create the consumer
        try(KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);) {

            // Subscribe to the topic
            consumer.subscribe(Collections.singletonList(topic));

            System.out.println(consumer.groupMetadata());

            // Poll for new data
            while (true) {
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
