package org.example.producer;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

import static org.apache.kafka.clients.CommonClientConfigs.SECURITY_PROTOCOL_CONFIG;
import static org.apache.kafka.common.config.SaslConfigs.SASL_JAAS_CONFIG;
import static org.apache.kafka.common.config.SaslConfigs.SASL_MECHANISM;

public class ProducerWithAckLab {
    public static void main(String[] args) {
        String account = args[0];
        String password = args[1];
        String topic = args[2];
        Dotenv dotenv = Dotenv.load();
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, dotenv.get("BOOTSTRAP_SERVER"));
        props.put(ProducerConfig.CLIENT_ID_CONFIG, "admin-test-" + account);
        // SECURITY_PROTOCOL=SASL_PLAINTEXT
        props.put(SECURITY_PROTOCOL_CONFIG, dotenv.get("SECURITY_PROTOCOL"));
        // SASL_MECHANISM=SCRAM-SHA-512
        props.put(SASL_MECHANISM, dotenv.get("SASL_MECHANISM"));

        //props.put("sasl.jaas.config", dotenv.get("JAAS"));
        String jaas = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"%s\" password=\"%s\";";
        props.put(SASL_JAAS_CONFIG, String.format(jaas, account, password));

        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        // acks = all => all replicas
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        try(Producer<String, String> producer = new KafkaProducer<>(props);) {
            String key = "58047611930";
            String value = """
                    {
                        "name": "John Doe",
                        "email": "john.doe@example.com"
                    }
                    """;

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
