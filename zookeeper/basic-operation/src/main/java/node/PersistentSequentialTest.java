package node;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

@Slf4j
public final class PersistentSequentialTest {
    private static final String zkPathBase = "/test/persistent";
    private static final String nodePrefix = "/node-faq-";
    private static final String zkPath = zkPathBase + nodePrefix;
    public static final String ZK_ADDRESS = "127.0.0.1:2183,127.0.0.1:2182,127.0.0.1:2181";

    public static void main(String[] args) {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(ZK_ADDRESS)
                .retryPolicy(retryPolicy)
                .build();

        try {
            client.start();
            for(int i=0; i<10; i++) {
                String path = client.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT_SEQUENTIAL)
                        .forPath(zkPath);
                log.info("create {}", path);
            }
            client.delete()
                    .deletingChildrenIfNeeded()
                    .forPath(zkPathBase);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
}
