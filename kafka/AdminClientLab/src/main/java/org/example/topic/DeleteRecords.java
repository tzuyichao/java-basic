package org.example.topic;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class DeleteRecords {
    static Logger logger = LoggerFactory.getLogger(DeleteRecords.class);

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
        String jaas = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"test\" password=\"test\";";
        props.put("sasl.jaas.config", jaas);
        //props.put("sasl.jaas.config", dotenv.get("JAAS"));
        try(AdminClient adminClient = KafkaAdminClient.create(props)) {
            String topic = "test.testopic.v1";
            Map<TopicPartition, ListOffsetsResult.ListOffsetsResultInfo> endOffsets = getTopicEndOffsets(adminClient, topic);

            // Step 2: Create a map of partitions with the end offset as the starting point for deletion
            Map<TopicPartition, RecordsToDelete> recordsToDelete = new HashMap<>();
            for (Map.Entry<TopicPartition, ListOffsetsResult.ListOffsetsResultInfo> entry : endOffsets.entrySet()) {
                recordsToDelete.put(entry.getKey(), RecordsToDelete.beforeOffset(entry.getValue().offset()));
            }
            DeleteRecordsResult deleteRecordsResult = adminClient.deleteRecords(recordsToDelete);
            for (KafkaFuture<DeletedRecords> future : deleteRecordsResult.lowWatermarks().values()) {
                future.get();
            }

            System.out.println("Topic " + topic + " has been emptied.");
        } catch (Exception e) {
            logger.error("Error listing topics: ", e);
        }
    }

    private static Map<TopicPartition, ListOffsetsResult.ListOffsetsResultInfo> getTopicEndOffsets(AdminClient adminClient, String topicName) throws ExecutionException, InterruptedException {
        // Get the partitions for the topic
        Map<TopicPartition, ListOffsetsResult.ListOffsetsResultInfo> endOffsets = new HashMap<>();
        for (TopicPartition partition : getPartitionsForTopic(adminClient, topicName)) {
            // Get the latest offset (high watermark) for each partition
            ListOffsetsResult result = adminClient.listOffsets(Collections.singletonMap(partition, null));
            endOffsets.put(partition, result.partitionResult(partition).get());
        }
        return endOffsets;
    }

    private static Set<TopicPartition> getPartitionsForTopic(AdminClient adminClient, String topicName) throws ExecutionException, InterruptedException {
        // Get metadata for the topic to retrieve its partitions
        return adminClient.describeTopics(Collections.singleton(topicName))
                .all()
                .get()
                .get(topicName)
                .partitions()
                .stream()
                .map(partitionInfo -> new TopicPartition(topicName, partitionInfo.partition()))
                .collect(Collectors.toSet());
    }

}
