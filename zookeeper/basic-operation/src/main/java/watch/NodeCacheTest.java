package watch;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Slf4j
public final class NodeCacheTest {
    private static final String zkPathBase = "/test/watch";
    public static final String ZK_ADDRESS = "127.0.0.1:2181";

    public static void main(String[] args) {
        try (CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(ZK_ADDRESS)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build()) {
            client.start();
            client.create()
                    .creatingParentsIfNeeded()
                    .forPath(zkPathBase, "init".getBytes(StandardCharsets.UTF_8));
            final NodeCache nodeCache = new NodeCache(client, zkPathBase, false);
            nodeCache.start(true);
            nodeCache.getListenable().addListener(new NodeCacheListener() {
                @Override
                public void nodeChanged() throws Exception {
                    if(nodeCache.getCurrentData() != null) {
                        log.info("stat: {}", nodeCache.getCurrentData().getStat());
                        log.info("Node data update, new data: {}", new String(nodeCache.getCurrentData().getData(), StandardCharsets.UTF_8));
                    } else {
                        log.info("currentData is null");
                    }
                }
            });
            client.setData()
                    .forPath(zkPathBase, "中文".getBytes(StandardCharsets.UTF_8));
            TimeUnit.SECONDS.sleep(1);
            client.delete()
                    .deletingChildrenIfNeeded()
                    .forPath(zkPathBase);
            TimeUnit.SECONDS.sleep(20);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
