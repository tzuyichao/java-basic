package group;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.nodes.GroupMember;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * https://curator.apache.org/curator-recipes/group-member.html
 */
@Slf4j
public class GroupMemberLab {
    public static void main(String[] args) {
        final int workerSize = 10;
        Executor executor = Executors.newFixedThreadPool(workerSize);

    }

    static class TestGroupMember implements Runnable {
        public static final String MEMBERSHIP_PATH = "/test/test-group";
        private GroupMember groupMember;

        public TestGroupMember(CuratorFramework curatorFramework) {
            groupMember = new GroupMember(curatorFramework, MEMBERSHIP_PATH, Thread.currentThread().getName(), "test".getBytes(StandardCharsets.UTF_8));
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
