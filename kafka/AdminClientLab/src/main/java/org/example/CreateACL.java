package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateAclsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.common.acl.AccessControlEntry;
import org.apache.kafka.common.acl.AclBinding;
import org.apache.kafka.common.acl.AclOperation;
import org.apache.kafka.common.acl.AclPermissionType;
import org.apache.kafka.common.resource.PatternType;
import org.apache.kafka.common.resource.ResourcePattern;
import org.apache.kafka.common.resource.ResourceType;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class CreateACL {
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
            String sourceTopicName = "test.testtopic.in.v0";
            String sinkTopicName = "test.testtopic.out.v0";
            ResourcePattern sourceResourcePattern = new ResourcePattern(ResourceType.TOPIC, sourceTopicName, PatternType.LITERAL);
            ResourcePattern sinkResourcePattern = new ResourcePattern(ResourceType.TOPIC, sinkTopicName, PatternType.LITERAL);
            CreateAclsResult createAclsResult = adminClient.createAcls(List.of(
                    new AclBinding(sourceResourcePattern, new AccessControlEntry("User:" + username, "*", AclOperation.READ, AclPermissionType.ALLOW)),
                    new AclBinding(sinkResourcePattern, new AccessControlEntry("User:" + username, "*", AclOperation.WRITE, AclPermissionType.ALLOW))
            ));
            createAclsResult.all().get();

            System.out.println("ACL created successfully.");
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
