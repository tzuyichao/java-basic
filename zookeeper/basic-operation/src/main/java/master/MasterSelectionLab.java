package master;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.concurrent.TimeUnit;

@Slf4j
public class MasterSelectionLab {
    private static final String zkPathBase = "/test/master";
    public static final String ZK_ADDRESS = "127.0.0.1:2181";

    public static void main(String[] args) {
        try(CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(ZK_ADDRESS)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build()) {
            client.start();
            LeaderSelector selector = new LeaderSelector(client, zkPathBase, new LeaderSelectorListener() {
                @Override
                public void takeLeadership(CuratorFramework client) throws Exception {
                    log.info("become master");
                    TimeUnit.SECONDS.sleep(3);
                    log.info("master completed. release master right");
                }

                @Override
                public void stateChanged(CuratorFramework client, ConnectionState newState) {

                }
            });
            selector.autoRequeue();
            selector.start();
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
