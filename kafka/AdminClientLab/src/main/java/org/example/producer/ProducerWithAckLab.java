package org.example.producer;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class ProducerWithAckLab {
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

        props.put("sasl.jaas.config", dotenv.get("JAAS"));
        //String jaas = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"PFMEA-DEV-Kafka-Account\" password=\"111\";";
        //props.put("sasl.jaas.config", jaas);

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // acks = all => all replicas
        props.put("acks", "all");
        try(Producer<String, String> producer = new KafkaProducer<>(props);) {
            String topic = "test.testopic.v1";
            String key = String.valueOf(System.currentTimeMillis());
            String value = "Hello, Kafka!";

            ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
            producer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    System.out.println("Message sent successfully. Offset: " + metadata.offset());
                } else {
                    System.out.println("Failed to send message: " + exception.getMessage());
                }
            });
        }
    }
}
