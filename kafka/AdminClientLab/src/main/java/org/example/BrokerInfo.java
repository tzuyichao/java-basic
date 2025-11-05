package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.Config;
import org.apache.kafka.clients.admin.DescribeConfigsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.common.config.ConfigResource;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class BrokerInfo {
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
        //String jaas = "org.apache.kafka.common.security.scram.ScramLoginModule required username=\"ACCOUNT\" password=\"PASSWORD\";";
        props.put("sasl.jaas.config", dotenv.get("JAAS"));
        getBrokerParamInfo(props);
    }

    private static void getBrokerParamInfo(Properties props) {
        try(AdminClient adminClient = KafkaAdminClient.create(props)) {
            ConfigResource brokerResource = new ConfigResource(ConfigResource.Type.BROKER, "1");
            DescribeConfigsResult result = adminClient.describeConfigs(Collections.singleton(brokerResource));
            Config config = result.all().get().get(brokerResource);

            System.out.println("message.max.bytes = " + config.get("message.max.bytes").value());
            System.out.println("replica.fetch.max.bytes = " + config.get("replica.fetch.max.bytes").value());
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
