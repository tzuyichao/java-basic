package watch;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Slf4j
public final class PathChildrenCacheTest {
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
            PathChildrenCache cache = new PathChildrenCache(client, zkPathBase, true);
            cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
            cache.getListenable().addListener(new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
                    switch (event.getType()) {
                        case CHILD_ADDED:
                            log.info("Child Add: {}", event.getData().getPath());
                            break;
                        case CHILD_UPDATED:
                            log.info("Child Update: {}", event.getData().getPath());
                            break;
                        case CHILD_REMOVED:
                            log.info("Child Delete: {}", event.getData().getPath());
                            break;
                        default:
                            break;
                    }
                }
            });

            String path = zkPathBase + "/c1";
            client.create()
                    .withMode(CreateMode.EPHEMERAL)
                    .forPath(path);
            TimeUnit.SECONDS.sleep(1);
            client.setData()
                    .forPath(path, "test".getBytes(StandardCharsets.UTF_8));
            TimeUnit.SECONDS.sleep(1);
            client.delete()
                    .forPath(path);
            TimeUnit.SECONDS.sleep(1);
            cache.close();

            client.delete()
                    .forPath(zkPathBase);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
