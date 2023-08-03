package org.example.producer;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class ProducerWithAckLab {
    public static void main(String[] args) {
        Properties prop = new Properties();
        prop.put("bootstrap.servers", "localhost:9093;localhost:9094;localhost:9095");
        prop.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        prop.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // acks = all => all replicas
        prop.put("acks", "all");
        Producer<String, String> producer = new KafkaProducer<>(prop);

        String topic = "test.topic.v0";
        String key = "my-key";
        String value = "Hello, Kafka!";

        ProducerRecord<String, String> record = new ProducerRecord<>(topic, key, value);
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception == null) {
                    System.out.println("Message sent successfully. Offset: " + metadata.offset());
                } else {
                    System.out.println("Failed to send message: " + exception.getMessage());
                }
            }
        });

        producer.close();
    }
}
