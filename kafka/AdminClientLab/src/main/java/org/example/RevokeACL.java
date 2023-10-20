package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DeleteAclsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.common.acl.AccessControlEntryFilter;
import org.apache.kafka.common.acl.AclBindingFilter;
import org.apache.kafka.common.acl.AclOperation;
import org.apache.kafka.common.acl.AclPermissionType;
import org.apache.kafka.common.resource.PatternType;
import org.apache.kafka.common.resource.ResourcePattern;
import org.apache.kafka.common.resource.ResourceType;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class RevokeACL {
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
            String username = "DQA-DFX-Kafka-Account";
            String topicName = "product.project-management.project.project-change.";
            ResourcePattern resourcePattern = new ResourcePattern(ResourceType.TOPIC, topicName, PatternType.PREFIXED);
            DeleteAclsResult deleteAclsResult = adminClient.deleteAcls(List.of(
                    new AclBindingFilter(resourcePattern.toFilter(), new AccessControlEntryFilter("User:" + username, "*", AclOperation.READ, AclPermissionType.ALLOW))
            ));
            deleteAclsResult.all().get();

            System.out.println("ACL revoke successfully.");
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
