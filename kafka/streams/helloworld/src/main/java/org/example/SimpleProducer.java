package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class SimpleProducer {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9093");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        try(Producer<String, String> producer = new KafkaProducer<>(props);) {
            String topic = "test.topic.v0";
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
