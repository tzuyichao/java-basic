package lock;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public final class InterProcessMutexLab {
    private static final String zkPath = "/test/mutex";
    public static final String ZK_ADDRESS = "127.0.0.1:2183,127.0.0.1:2182,127.0.0.1:2181";

    private int counter;
    private ExecutorService executorService;

    public void run(CuratorFramework client) {
        executorService = Executors.newFixedThreadPool(10);

            final InterProcessMutex mutex = new InterProcessMutex(client, zkPath);
            for(int i=0; i<10; i++) {
                executorService.submit(() -> {
                    try {
                        mutex.acquire();
                        for(int j=0; j<10; j++) {
                            counter++;
                        }
                        TimeUnit.SECONDS.sleep(1);
                        log.info("counter: {}", counter);
                        mutex.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

        }
    }

    public static void main(String[] args) {
        InterProcessMutexLab lab = new InterProcessMutexLab();
        try (CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(ZK_ADDRESS)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build()) {
            client.start();
            lab.run(client);
            TimeUnit.SECONDS.sleep(30);
            log.info("counter: {}", lab.counter);
            lab.executorService.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
