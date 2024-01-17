package org.example.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class Consumer2 {
    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.17.0.3:9092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "terence-test-consumer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest"); // "earliest" or "latest"
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");


        try(KafkaConsumer consumer = new KafkaConsumer<String, String>(props);) {

            consumer.subscribe(Collections.singletonList("my_connect_configs"));

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                records.forEach(record -> {
                    System.out.printf("Consumed record with key %s and value %s%n", record.key(), record.value());
                    consumer.commitAsync();
                });
            }
        }
    }
}
