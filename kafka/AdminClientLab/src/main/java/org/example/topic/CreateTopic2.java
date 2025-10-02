package org.example.topic;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class CreateTopic2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("fetch.max.bytes", 1024);
        props.put("enable.auto.commit", true);
        props.put("auto.commit.interval.ms", 1000);
        createTopic(props, "demo.sink.in");
    }

    private static void createTopic(Properties props, String topicName) throws InterruptedException, ExecutionException {
        try(AdminClient adminClient = KafkaAdminClient.create(props)) {
            NewTopic newTopic = new NewTopic(topicName, 1, (short) 1);
            adminClient.createTopics(java.util.Collections.singleton(newTopic)).all().get();
            System.out.println("Topic created successfully.");
        }
    }
}
