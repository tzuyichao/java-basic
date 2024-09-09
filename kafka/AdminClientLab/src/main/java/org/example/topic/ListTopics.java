package org.example.topic;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;

import java.util.Properties;

public class ListTopics {
    public static void listTopics() {
        System.out.println("ListTopics");
        Dotenv dotenv = Dotenv.configure().filename(".customer").load();
        Properties props = new Properties();
        props.put("bootstrap.servers", dotenv.get("BOOTSTRAP_SERVER"));
        props.put("fetch.max.bytes", 1024);
        props.put("enable.auto.commit", true);
        props.put("auto.commit.interval.ms", 1000);
        props.put("security.protocol", dotenv.get("SECURITY_PROTOCOL"));
        props.put("sasl.mechanism", dotenv.get("SASL_MECHANISM"));
        props.put("auto.offset.reset","earliest");
        props.put("sasl.jaas.config", dotenv.get("JAAS"));
        try(AdminClient adminClient = KafkaAdminClient.create(props)) {
            adminClient.listTopics().names().get().forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Error listing topics: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ListTopics.listTopics();
    }
}
