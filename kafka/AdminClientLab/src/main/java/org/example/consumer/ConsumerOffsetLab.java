package org.example.consumer;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import static java.util.stream.Collectors.*;

public class ConsumerOffsetLab {
    private static final Logger logger = LoggerFactory.getLogger(ConsumerOffsetLab.class);
    public static void main(String[] args) {
        String topic = "test.testopic.v0";
        Dotenv dotenv = Dotenv.load();
        Properties props = new Properties();
        props.put("bootstrap.servers", dotenv.get("BOOTSTRAP_SERVER"));
        // SECURITY_PROTOCOL=SASL_PLAINTEXT
        props.put("security.protocol", dotenv.get("SECURITY_PROTOCOL"));
        // SASL_MECHANISM=SCRAM-SHA-512
        props.put("sasl.mechanism", dotenv.get("SASL_MECHANISM"));
        props.put("auto.offset.reset","earliest");
        props.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
        //String jaas = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"ACCOUNT\" password=\"PASSWORD\";";
        props.put("sasl.jaas.config", dotenv.get("JAAS"));

        props.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // Create the consumer
        try(KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
            AdminClient adminClient = KafkaAdminClient.create(props)) {
            ListTopicsOptions options = new ListTopicsOptions();
            options.listInternal(true);
            ListTopicsResult listTopicsResult = adminClient.listTopics(options);
            Set<String> topics = listTopicsResult.names().get();
            List<TopicPartition> collect = adminClient.describeTopics(topics).topicNameValues().values()
                    .stream()
                    .flatMap(future -> {
                        try {
                            TopicDescription topicDescription = future.get();
                            return topicDescription.partitions().stream()
                                    .map(topicPartitionInfo -> new TopicPartition(topicDescription.name(), topicPartitionInfo.partition()));
                        } catch (InterruptedException | ExecutionException e) {
                            throw new RuntimeException(e);
                        }
                    }).collect(toList());
            Map<TopicPartition, Long> begins = consumer.beginningOffsets(collect);
            Map<TopicPartition, Long> ends = consumer.endOffsets(collect);
            Map<String, List<Partition.Offsets>> offsets = begins.entrySet().stream()
                    .collect(groupingBy(entry -> entry.getKey().topic(), mapping(begin -> new Partition.Offsets(begin.getKey().partition(), begin.getValue(), ends.get(begin.getKey())), toList())));
            logger.info("Offsets: {}", offsets);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Partition {

        public static class Offsets {
            private final int partition;
            private final long begin;
            private final long end;

            public Offsets(int partition, long begin, long end) {
                this.partition = partition;
                this.begin = begin;
                this.end = end;
            }

            public int getPartition() {
                return partition;
            }

            public long getBegin() {
                return begin;
            }

            public long getEnd() {
                return end;
            }

            @Override
            public String toString() {
                return "Offsets{" +
                        "partition=" + partition +
                        ", begin=" + begin +
                        ", end=" + end +
                        '}';
            }
        }
    }
}
