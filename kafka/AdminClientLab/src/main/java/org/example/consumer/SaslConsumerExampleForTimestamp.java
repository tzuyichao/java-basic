package org.example.consumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collections;
import java.util.Properties;

public class SaslConsumerExampleForTimestamp {
    private static final Logger logger = LoggerFactory.getLogger(SaslConsumerExampleForTimestamp.class);
    public static void main(String[] args) {
        String bootstrapServers = "datagovstg-kfk04.deltaww.com:9093";
        String topic = "test.testopic.v1";
        String groupId = "compute";

        // Kafka consumer configuration settings
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // SASL configuration
        properties.setProperty("security.protocol", "SASL_PLAINTEXT");
        properties.setProperty("sasl.mechanism", "SCRAM-SHA-512");
        properties.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"aaaa\" password=\"qqqq\";");

        String file = "result.csv";

        // Create the consumer
        try(KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(file), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        ) {


            // Subscribe to the topic
            consumer.subscribe(Collections.singletonList(topic));

            System.out.println(consumer.groupMetadata());

            ObjectMapper mapper = new ObjectMapper();

            boolean shouldContinue = true;
            // Poll for new data
            while (shouldContinue) {
                ConsumerRecords<String, String> records =
                        consumer.poll(Duration.ofMillis(100));

                for (ConsumerRecord<String, String> record : records){
                    long messageTimestamp = record.timestamp();
                    LocalDate messageDate = Instant.ofEpochMilli(messageTimestamp)
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();

                    JsonNode key = mapper.readTree(record.key());

                    String line = messageDate + "\t" + key.get("payload").get("Dept_Code").asText();
                    System.out.println(line);
                    writer.write(line);
                    writer.newLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
