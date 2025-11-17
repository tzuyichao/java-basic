package org.example.consumer;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ConsumerGroupListing;
import org.apache.kafka.clients.admin.ListOffsetsResult;
import org.apache.kafka.clients.admin.OffsetSpec;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.util.*;
import java.util.concurrent.ExecutionException;

public class ConsumerGroupLag {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
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
        props.put("sasl.jaas.config", dotenv.get("JAAS"));
        getConsumerGroupLag(props);
    }

    private static void getConsumerGroupLag(Properties props) throws ExecutionException, InterruptedException {
        try(AdminClient client = AdminClient.create(props)) {
            Collection<ConsumerGroupListing> consumerGroupListings = client.listConsumerGroups().all().get();
            for(ConsumerGroupListing consumerGroupListing : consumerGroupListings) {
                String groupId = consumerGroupListing.groupId();
                Map<String, Map<TopicPartition, OffsetAndMetadata>> offsetMap = client.listConsumerGroupOffsets(groupId).all().get();
                System.out.println(groupId);
                Map<TopicPartition, OffsetAndMetadata> commitedOffsets = offsetMap.getOrDefault(groupId, Collections.EMPTY_MAP);
                if(commitedOffsets.isEmpty()) {
                    System.out.println("No commited offsets found");
                    continue;
                }
                Map<TopicPartition, OffsetSpec> requestLatestOffsets = new HashMap<>();
                for(TopicPartition tp: commitedOffsets.keySet()) {
                    requestLatestOffsets.put(tp, OffsetSpec.latest());
                }
                ListOffsetsResult latestOffsetsResult = client.listOffsets(requestLatestOffsets);
                Map<TopicPartition, ListOffsetsResult.ListOffsetsResultInfo> latestOffsetsInfo = latestOffsetsResult.all().get();

                Map<String, Long> topicTotalLag = new HashMap<>();
                for (Map.Entry<TopicPartition, OffsetAndMetadata> entry : commitedOffsets.entrySet()) {
                    TopicPartition tp = entry.getKey();
                    OffsetAndMetadata committed = entry.getValue();

                    ListOffsetsResult.ListOffsetsResultInfo info = latestOffsetsInfo.get(tp);
                    if (info == null) {
                        System.out.printf("    %s-%d: committed=%d, latest=UNKNOWN, lag=UNKNOWN (partition not found)%n",
                                tp.topic(), tp.partition(), committed.offset());
                        continue;
                    }

                    long latestOffset = info.offset();
                    long committedOffset = committed.offset();

                    long lag = Math.max(0L, latestOffset - committedOffset);

                    topicTotalLag.merge(tp.topic(), lag, Long::sum);

                    System.out.printf("    %s-%d: committed=%d, latest=%d, lag=%d%n",
                            tp.topic(), tp.partition(), committedOffset, latestOffset, lag);
                }
            }
        }
    }
}
