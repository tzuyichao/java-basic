package watch;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

@Slf4j
public final class TreeCacheTest {
    private static final String zkPathBase = "/test/watch";
    public static final String ZK_ADDRESS = "127.0.0.1:2183,127.0.0.1:2182,127.0.0.1:2181";

    public static void main(String[] args) {
        try(CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(ZK_ADDRESS)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build()) {
            client.start();
            String path = client.create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(zkPathBase, "init".getBytes(StandardCharsets.UTF_8));
            TreeCache cache = new TreeCache(client, path);
            cache.getListenable().addListener(new TreeCacheListener() {
                @Override
                public void childEvent(CuratorFramework client, TreeCacheEvent event) throws Exception {
                    if(event.getData() == null) {
                        log.info("data is null type: {}", event.getType());
                        return;
                    }
                    switch (event.getType()) {
                        case NODE_ADDED:
                            log.info("{} [TreeCache] Node added {}", Thread.currentThread().getName(), event.getData().getPath());
                            break;
                        case NODE_UPDATED:
                            log.info("{} [TreeCache] Node updated path:{}, data", Thread.currentThread().getName(), event.getData().getPath(), new String(event.getData().getData(), StandardCharsets.UTF_8));
                            break;
                        case NODE_REMOVED:
                            log.info("{} [TreeCache] Node deleted {}", Thread.currentThread().getName(), event.getData().getPath());
                            break;
                        default:
                            break;
                    }
                }
            });
            cache.start();

            log.info("{} create nodes", Thread.currentThread().getName());
            for(int i=0; i<2; i++) {
                client.create()
                        .withMode(CreateMode.EPHEMERAL)
                        .forPath(zkPathBase + "/n" + i, "init".getBytes(StandardCharsets.UTF_8));
            }
            TimeUnit.SECONDS.sleep(2);

            log.info("{} setDate for exist nodes", Thread.currentThread().getName());
            for(int i=0; i<2; i++) {
                client.setData()
                        .forPath(zkPathBase + "/n" + i, "test".getBytes(StandardCharsets.UTF_8));
            }
            TimeUnit.SECONDS.sleep(2);

            log.info("{} delete {}", Thread.currentThread().getName(), zkPathBase);
            client.delete().deletingChildrenIfNeeded().forPath(zkPathBase);
            TimeUnit.SECONDS.sleep(2);

            cache.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
