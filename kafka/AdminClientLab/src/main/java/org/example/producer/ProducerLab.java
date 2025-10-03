package org.example.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ProducerLab {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("fetch.max.bytes", 1024);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        send(props, "demo.sink.in");
    }

    private static void send(Properties props, String topicName) throws InterruptedException, ExecutionException {
        try(KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);) {
            String key = "{}";
            String value = "{\"payload\": {\"DeptCode\": \"Test\", \"Name\": \"Test\"}} }}";
            ProducerRecord<String, String> record = new ProducerRecord<>(topicName, key, value);
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
