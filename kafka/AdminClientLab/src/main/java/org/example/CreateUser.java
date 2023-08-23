package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.*;

import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class CreateUser {
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
        try(AdminClient adminClient = KafkaAdminClient.create(props)) {
            String username = "terence.chao";
            String password = "1qaz@WSX";

            UserScramCredentialUpsertion userCredentials = new UserScramCredentialUpsertion(username, new ScramCredentialInfo(ScramMechanism.SCRAM_SHA_512, 4096), password);

            adminClient.alterUserScramCredentials(Collections.singletonList(userCredentials)).all().get();

            System.out.println("User created successfully.");


        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
