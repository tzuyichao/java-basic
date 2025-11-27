package org.example.topic;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.common.quota.ClientQuotaAlteration;
import org.apache.kafka.common.quota.ClientQuotaEntity;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class AlterEntityConfig {
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

        try(AdminClient adminClient = AdminClient.create(props)) {
            Map<String, String> entries = Map.of(
                    ClientQuotaEntity.USER, "Quota-Kafka-Account"
            );
            ClientQuotaEntity entity = new ClientQuotaEntity(entries);

            ClientQuotaAlteration.Op op =
                    new ClientQuotaAlteration.Op("producer_byte_rate", 1024.0);

            ClientQuotaAlteration alteration =
                    new ClientQuotaAlteration(entity, List.of(op));

            adminClient.alterClientQuotas(List.of(alteration)).all().get();
        }
    }




}
