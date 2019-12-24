package node;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.StandardCharsets;

@Slf4j
public final class CrudTest {
    private static void read(CuratorFramework client, String zkPath) throws Exception {
        Stat stat = client.checkExists().forPath(zkPath);
        if(stat != null) {
            byte[] payload = client.getData().forPath(zkPath);
            String data = new String(payload, StandardCharsets.UTF_8);
            log.info("retrieve data: {}", data);
        } else {
            log.error("zkPath {} does not exist", zkPath);
        }
    }

    public static void main(String[] args) {
        final String zkPath = "/test/crud/node-1";
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2182,127.0.0.1:2183,127.0.0.1:2181", retryPolicy);
        try {
            client.start();
            Stat stat = client.checkExists().forPath(zkPath);
            if(null == stat) {
                String data = "hello, world!";
                byte[] payload = data.getBytes(StandardCharsets.UTF_8);
                String result = client.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(zkPath, payload);
                log.info("result: {}", result);
                read(client, zkPath);
                payload = "HELLO".getBytes(StandardCharsets.UTF_8);
                Stat setDataStat = client.setData().forPath(zkPath, payload);
                log.info("setData Stat: {}", setDataStat);
                byte[] readout = client.getData().forPath(zkPath);
                log.info("getData Data: {}", new String(readout, StandardCharsets.UTF_8));
            } else {
                log.info("has node: {}", zkPath);
                read(client, zkPath);
                client.delete().forPath(zkPath);
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
}
