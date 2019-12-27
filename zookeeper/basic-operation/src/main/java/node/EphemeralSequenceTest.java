package node;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public final class EphemeralSequenceTest {
    private static final String zkPathBase = "/test/ephemeral";
    private static final String nodePrefix = "/faq-dev-";
    private static final String zkPath = zkPathBase + nodePrefix;
    public static final String ZK_ADDRESS = "127.0.0.1:2183,127.0.0.1:2182,127.0.0.1:2181";

    public static void main(String[] args) {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework clientFirst = CuratorFrameworkFactory.builder()
                .connectString(ZK_ADDRESS)
                .retryPolicy(retryPolicy)
                .build();

        CuratorFramework clientSecond = CuratorFrameworkFactory.builder()
                .connectString(ZK_ADDRESS)
                .retryPolicy(retryPolicy)
                .build();

        try {
            clientFirst.start();
            Collection<String> children = clientFirst.getChildren().forPath(zkPathBase);
            log.info("{} Children Size: {}", zkPathBase, children.size());
            for(int i=0; i<10; i++) {
                String path = clientFirst.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                        .forPath(zkPath);
                log.info("clientFirst create {}", path);
                log.info("clientFirst faq id: {}", path.substring(zkPath.length()));
            }
            clientFirst.close();
            log.info("Sleep for 60 seconds");
            TimeUnit.SECONDS.sleep(60);
            clientSecond.start();
            children = clientSecond.getChildren().forPath(zkPathBase);
            log.info("{} Children Size: {}", zkPathBase, children.size());
            for(int i=0; i<10; i++) {
                String path = clientSecond.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL_SEQUENTIAL)
                        .forPath(zkPath);
                log.info("clientSecond create {}", path);
                log.info("clientSecond faq id: {}", path.substring(zkPath.length()));
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if(clientFirst.getState() == CuratorFrameworkState.STARTED) {
                clientFirst.close();
            }
            clientSecond.close();
        }
    }
}
