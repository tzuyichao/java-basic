package app;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.KafkaFuture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Collections;
import java.util.Properties;
import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class TopicOperationTest {
    @Container
    private static final KafkaContainer KAFKA_CONTAINER = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"));

    private Admin admin;

    @BeforeEach
    void setup() {
        Properties properties = new Properties();
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_CONTAINER.getBootstrapServers());
        admin = Admin.create(properties);
    }

    @Test
    void givenTopicName_whenCreateNewTopic_thenTopicIsCreated() throws Exception {
        String topicName = "test-topic";
        int partitions = 1;
        short replicationFactor = 1;
        NewTopic newTopic = new NewTopic(topicName, partitions, replicationFactor);
        CreateTopicsResult createTopicsResult = admin.createTopics(Collections.singleton(newTopic));
        KafkaFuture<Void> future = createTopicsResult.values().get(topicName);
        future.get();

        String topicCommand = "/usr/bin/kafka-topics --bootstrap-server=localhost:9092 --list";
        String stdout = KAFKA_CONTAINER.execInContainer("/bin/sh", "-c", topicCommand)
                .getStdout();

        assertThat(stdout).contains("test-topic");
    }
}
