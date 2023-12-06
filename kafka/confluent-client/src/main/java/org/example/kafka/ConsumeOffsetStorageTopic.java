package org.example.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.kafka.util.ConnectionUtil;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumeOffsetStorageTopic {
    public static void main(String[] args) {
        Properties props = ConnectionUtil.props();

        try(KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);) {

            consumer.subscribe(Collections.singletonList("datagov.pipeline.mgt.connect-offsets"));

            String latestValue = null;
            String latestKey = null;
            long latestTimestamp = 0;
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(30));
                for (ConsumerRecord<String, String> record : records) {
                    if (record.key().contains("datagov.pipeline.mes_cdm_prd_db.sfcs.c_std_mfg_plant_t.source.v151")
                            && record.timestamp() > latestTimestamp) {
                        latestTimestamp = record.timestamp();
                        latestKey = record.key();
                        latestValue = record.value();
                    }
                }
                if (records.isEmpty()) {
                    break;
                }
            }
            if (latestKey != null) {
                System.out.println("Latest Key: " + latestKey);
                System.out.println("Latest Value: " + latestValue);
                System.out.println("Timestamp: " + latestTimestamp);
            } else {
                System.out.println("No matching records found.");
            }
        }
    }
}
