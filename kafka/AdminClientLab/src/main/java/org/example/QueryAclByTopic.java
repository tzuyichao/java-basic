package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeAclsResult;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.common.acl.AccessControlEntryFilter;
import org.apache.kafka.common.acl.AclBindingFilter;
import org.apache.kafka.common.resource.PatternType;
import org.apache.kafka.common.resource.ResourcePatternFilter;
import org.apache.kafka.common.resource.ResourceType;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class QueryAclByTopic {
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

            ResourcePatternFilter topicFilter = new ResourcePatternFilter(
                    ResourceType.TOPIC,
                    "product.project-management.project.activity-change.",
                    PatternType.PREFIXED
            );

            AclBindingFilter filter = new AclBindingFilter(topicFilter, AccessControlEntryFilter.ANY);

            DescribeAclsResult describeResult = adminClient.describeAcls(filter);

            describeResult.values().get().forEach(aclBinding -> {
                if(aclBinding.entry().operation().name().equals("READ") || aclBinding.entry().operation().name().equals("WRITE")) {
                    System.out.printf("%-40s\t%-10s\t%-10s\n", aclBinding.entry().principal(), aclBinding.entry().operation(), aclBinding.entry().permissionType());
                }
            });
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
