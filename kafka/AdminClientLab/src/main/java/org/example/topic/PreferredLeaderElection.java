package org.example.topic;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.ElectLeadersOptions;
import org.apache.kafka.clients.admin.ElectLeadersResult;
import org.apache.kafka.common.ElectionType;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.TopicPartition;
import org.example.config.EnvConfig;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

public class PreferredLeaderElection {
    public static void main(String[] args) {
        Properties props = EnvConfig.getConfiguration();

        try (AdminClient adminClient = AdminClient.create(props)) {
            Set<TopicPartition> target = new HashSet<>();
            target.add(new TopicPartition("tp.quality-dfx.dqa.4", 2));
            target.add(new TopicPartition("datagov.pipeline.synoa_prd_db.dbo.sync_site_role_2871", 0));
            target.add(new TopicPartition("quality.quality-alert.product-line-unblock.v0.wj3", 2));
            target.add(new TopicPartition("test.testtopic.v12", 1));
            target.add(new TopicPartition("datagov.pipeline.mgt.connect-offset", 11));
            ElectLeadersResult electLeadersResult = adminClient.electLeaders(ElectionType.PREFERRED, target, new ElectLeadersOptions());
            KafkaFuture<Void> future = electLeadersResult.all();
            future.get();

            System.out.println("Leader election completed.");
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
