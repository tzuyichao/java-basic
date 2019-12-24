package connect;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;

public final class ClientConnect {
    public static void main(String[] args) {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient("127.0.0.1:2183,127.0.0.1:2182,127.0.0.1:2181", retryPolicy);
        try {
            curatorFramework.start();
            CuratorFrameworkState state = curatorFramework.getState();
            System.out.println(state.toString());
            System.out.println(curatorFramework.toString());
        } finally {
            curatorFramework.close();
        }
    }
}
