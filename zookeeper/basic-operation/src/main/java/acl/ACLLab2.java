package acl;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public final class ACLLab2 {
    private static final String zkPathBase = "/test/acl";
    private static final String zkApp1Path = zkPathBase + "/app1";
    public static final String ZK_ADDRESS = "127.0.0.1:2181";

    public static void main(String[] args) {
        log.info("ACL Lab 2");

        String aclAdminUsername = "adminuser";
        String aclAdminPassword = "adminpass";
        String fullControlAuth = aclAdminUsername + ":" + aclAdminPassword;

        String app1Username = "app1";
        String app1Password = "app1pass";
        String app1Auth = app1Username + ":" + app1Password;

        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework adminClient = CuratorFrameworkFactory.builder()
                .connectString(ZK_ADDRESS)
                .retryPolicy(retryPolicy)
                .authorization("digest", fullControlAuth.getBytes())
                .build();

        CuratorFramework app1Client = CuratorFrameworkFactory.builder()
                .connectString(ZK_ADDRESS)
                .retryPolicy(retryPolicy)
                .authorization("digest", app1Auth.getBytes())
                .build();

        try {
            adminClient.start();
            List<ACL> baseAclList = new ArrayList<>();
            String fullControlDigest = DigestAuthenticationProvider.generateDigest(fullControlAuth);
            ACL fullControlAcl = new ACL(ZooDefs.Perms.ALL, new Id("digest", fullControlDigest));
            baseAclList.add(fullControlAcl);
            adminClient.create()
                    .creatingParentsIfNeeded()
                    .withACL(baseAclList)
                    .forPath(zkPathBase);
            log.info("create {} with full control acl to admin", zkPathBase);

            List<ACL> app1AclList = new ArrayList<>();
            String app1ControlDigest = DigestAuthenticationProvider.generateDigest(app1Auth);
            ACL appControlAcl = new ACL(ZooDefs.Perms.ALL, new Id("digest", app1ControlDigest));
            app1AclList.add(appControlAcl);
            app1AclList.add(fullControlAcl);
            adminClient.create()
                    .withACL(app1AclList)
                    .forPath(zkApp1Path);
            log.info("create {} with full control acl to app1", zkApp1Path);
            app1Client.start();
            List<ACL> app1ACLList = app1Client.getACL()
                    .forPath(zkApp1Path);
            app1ACLList.forEach(acl -> log.info(acl.toString()));

            String test = app1Client.create()
                    .forPath(zkApp1Path + "/test", "test".getBytes(StandardCharsets.UTF_8));
            log.info("app1 create {}", test);
            String nodeShouldNotAllowed = zkPathBase + "/test";
            try {
                app1Client.create()
                        .forPath(nodeShouldNotAllowed, "hello".getBytes(StandardCharsets.UTF_8));
                log.info("app1 create {} SUCCESS", nodeShouldNotAllowed);
            } catch(Exception e) {
                log.info("app1 create {} failed", nodeShouldNotAllowed);
            }
            app1Client.delete()
                    .deletingChildrenIfNeeded()
                    .forPath(zkApp1Path);
            // 如果admin沒有app完整權限，而app1沒有刪除的話這個操作會失敗
            // KeeperErrorCode = NoAuth for /test/acl/app1
            adminClient.delete()
                    .deletingChildrenIfNeeded()
                    .forPath(zkPathBase);
            log.info("admin delete {}", zkPathBase);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            adminClient.close();
            app1Client.close();
        }
    }
}
