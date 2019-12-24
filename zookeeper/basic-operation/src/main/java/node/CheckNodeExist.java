package node;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

public final class CheckNodeExist {
    public static final String ZK_ADDRESS = "127.0.0.1:2183,127.0.0.1:2182,127.0.0.1:2181";
    public static void main(String[] args) {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(ZK_ADDRESS, retryPolicy);
        try {
            client.start();
            Stat stat = client.checkExists().forPath("/test/node-1");
            if(stat == null) {
                System.out.println("node does not exist");
            } else {
                System.out.println(stat);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
}
