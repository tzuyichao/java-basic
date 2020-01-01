package async;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public final class CreateNodeBackgroundLab {
    private static final String zkPathBase = "/test/async";
    public static final String ZK_ADDRESS = "127.0.0.1:2181";
    private static CountDownLatch semaphore = new CountDownLatch(2);
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        try (CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(ZK_ADDRESS)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build()) {
            client.start();
            Stat stat = client.checkExists().forPath(zkPathBase);
            if(stat == null) {
                log.info("node does not exist");
                client.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL)
                        .inBackground(new BackgroundCallback() {
                            @Override
                            public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                                log.info("event[code: {}, type: {}]", event.getResultCode(), event.getType());
                                log.info("Thread of processResult: {}", Thread.currentThread().getName());
                                semaphore.countDown();
                            }
                        }, executorService)
                        .forPath(zkPathBase, "init".getBytes(StandardCharsets.UTF_8));
                client.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL)
                        .inBackground(new BackgroundCallback() {
                            @Override
                            public void processResult(CuratorFramework client, CuratorEvent event) throws Exception {
                                log.info("event[code: {}, type: {}]", event.getResultCode(), event.getType());
                                log.info("Thread of processResult: {}", Thread.currentThread().getName());
                                semaphore.countDown();
                            }
                        })
                        .forPath(zkPathBase, "init".getBytes(StandardCharsets.UTF_8));
            }
            semaphore.await();
            executorService.shutdown();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
