package group;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.nodes.GroupMember;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * https://curator.apache.org/curator-recipes/group-member.html
 */
@Slf4j
public class GroupMemberLab {
    public static final String ZK_ADDRESS = "127.0.0.1:2181";

    public static void main(String[] args) {
        final int workerSize = 10;
        ExecutorService executor = Executors.newFixedThreadPool(workerSize);
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        try (CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(ZK_ADDRESS)
                .retryPolicy(retryPolicy)
                .build()) {
            client.start();
            for (int i = 0; i < 10; i++) {
                executor.submit(new TestGroupMember(client, i));
            }
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdownNow();
    }

    static class TestGroupMember implements Runnable {
        public static final String MEMBERSHIP_PATH = "/test/test-group";
        private GroupMember groupMember;

        public TestGroupMember(CuratorFramework curatorFramework, int id) {
            groupMember = new GroupMember(curatorFramework, MEMBERSHIP_PATH, "id:" + id, "test".getBytes(StandardCharsets.UTF_8));
            groupMember.start();
        }

        @Override
        public void run() {
            try {
                log.info("{} running...", Thread.currentThread().getName());
                TimeUnit.SECONDS.sleep(SecureRandom.getInstanceStrong().nextInt(10));
                groupMember.getCurrentMembers().forEach((id, payload) -> {
                    log.info("id: {}, payload: {}", id, new String(payload, StandardCharsets.UTF_8));
                });
                log.info("Exit Group");
                groupMember.close();
            } catch(InterruptedException | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
    }
}
