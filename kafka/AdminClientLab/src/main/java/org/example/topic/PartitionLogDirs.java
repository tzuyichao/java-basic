package org.example.topic;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.requests.DescribeLogDirsResponse;

import java.util.*;

public class PartitionLogDirs {
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
        props.put("auto.offset.reset", "earliest");
        //String jaas = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"ACCOUNT\" password=\"PASSWORD\";";
        props.put("sasl.jaas.config", dotenv.get("JAAS"));
        try (AdminClient adminClient = AdminClient.create(props)) {
            Collection<Node> brokers = adminClient.describeCluster().nodes().get();
            List<Integer> brokerIds = new ArrayList<>();
            for (Node node : brokers) {
                brokerIds.add(node.id());
            }
            Map<Integer, Map<String, DescribeLogDirsResponse.LogDirInfo>> logDirInfoMap = adminClient.describeLogDirs(brokerIds).all().get();
            for(int id : brokerIds) {
                Map<String, DescribeLogDirsResponse.LogDirInfo> logDirInfo = logDirInfoMap.get(id);
                for (Map.Entry<String, DescribeLogDirsResponse.LogDirInfo> entry : logDirInfo.entrySet()) {
                    String logDir = entry.getKey();
                    DescribeLogDirsResponse.LogDirInfo info = entry.getValue();
                    System.out.printf("Broker: %d, LogDir: %s %n", id, logDir);
                    for (Map.Entry<TopicPartition, DescribeLogDirsResponse.ReplicaInfo> replicaEntry : info.replicaInfos.entrySet()) {
                        DescribeLogDirsResponse.ReplicaInfo replicaInfo = replicaEntry.getValue();
                        System.out.printf("\tPartition: %s, Size: %d, OffsetLag: %d%n",
                                replicaEntry.getKey(), replicaInfo.size, replicaInfo.offsetLag);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
