package node;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.transaction.CuratorOp;
import org.apache.curator.framework.api.transaction.CuratorTransactionResult;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.StandardCharsets;
import java.util.Collection;

@Slf4j
public final class TransactionTest {
    public static final String ZK_ADDRESS = "127.0.0.1:2183,127.0.0.1:2182,127.0.0.1:2181";

    public static void main(String[] args) {
        ExponentialBackoffRetry retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString(ZK_ADDRESS)
                .retryPolicy(retryPolicy)
                .build();
        try {
            client.start();
            CuratorOp createOp = client.transactionOp()
                    .create()
                    .forPath("/a/path", "some data".getBytes(StandardCharsets.UTF_8));
            CuratorOp setDataOp = client.transactionOp()
                    .setData()
                    .forPath("/another/path", "other data".getBytes(StandardCharsets.UTF_8));
            CuratorOp deleteOp = client.transactionOp()
                    .delete()
                    .forPath("/yet/another/path");

            Collection<CuratorTransactionResult> results = client.transaction().forOperations(createOp, setDataOp, deleteOp);
            for(CuratorTransactionResult result: results) {
                log.info("path: {}, tyep: {}", result.getForPath(), result.getType());
            }
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                Stat stat = client.checkExists().forPath("/a/path");
                if (null == stat) {
                    log.info("/a/path does not exist");
                } else {
                    log.info("/a/path does exist");
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
            CloseableUtils.closeQuietly(client);
        }
    }
}
