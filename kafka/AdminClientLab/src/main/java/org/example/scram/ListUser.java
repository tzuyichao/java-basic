package org.example.scram;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeUserScramCredentialsResult;
import org.apache.kafka.clients.admin.UserScramCredentialsDescription;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class ListUser {
    private static Properties getEnv() {
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
        return props;
    }

    public static void main(String[] args) {
        try(AdminClient adminClient = AdminClient.create(getEnv())) {
            DescribeUserScramCredentialsResult r = adminClient.describeUserScramCredentials();
            List<String> users = r.users().get();
            System.out.println("SCRAM users: " + users);

            Map<String, UserScramCredentialsDescription> desc = r.all().get();
            for(Map.Entry<String, UserScramCredentialsDescription> entry : desc.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
