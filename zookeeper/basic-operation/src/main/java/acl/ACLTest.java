package acl;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ACLTest {
    private static final String zkPath = "/test/abc";
    public static final String ZK_ADDRESS = "127.0.0.1:2183,127.0.0.1:2182,127.0.0.1:2181";

    public static void main(String[] args) {
        String zkUsername = "adminuser";
        String zkPassword = "adminpass";
        String fullControlAuth = zkUsername + ":" + zkPassword;

        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(ZK_ADDRESS)
                .retryPolicy(retryPolicy)
                .authorization("digest", fullControlAuth.getBytes())
                .build();

        CuratorFramework client2 = CuratorFrameworkFactory.builder()
                .connectString(ZK_ADDRESS)
                .retryPolicy(retryPolicy)
                .build();
                //.newClient(ZK_ADDRESS, retryPolicy);
        try {
            client.start();
            client2.start();
            String data = "hello, world!";
            List<ACL> aclList = new ArrayList<>();
            String fullControlDigest = DigestAuthenticationProvider.generateDigest(fullControlAuth);
            ACL fullControlAcl = new ACL(ZooDefs.Perms.ALL, new Id("digest", fullControlDigest));
            aclList.add(fullControlAcl);
            byte[] payload = data.getBytes(StandardCharsets.UTF_8);
            String result = client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .withACL(aclList)
                    .forPath(zkPath, payload);
            log.info(result);
            List<ACL> acls = client.getACL()
                    .forPath(zkPath);
            acls.forEach(acl -> log.info("ACL: {}", acl));
            log.info("client2 setData:");
            client2.setData()
                    .forPath(zkPath, "test".getBytes(StandardCharsets.UTF_8));
            log.info("client setData:");
            client.setData()
                    .forPath(zkPath, "test".getBytes(StandardCharsets.UTF_8));
            log.info("client2 delete node:");
            client2.delete()
                    .forPath(zkPath);
            log.info("client delete node:");
            client.delete()
                    .forPath(zkPath);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
            client2.close();
        }
    }
}
