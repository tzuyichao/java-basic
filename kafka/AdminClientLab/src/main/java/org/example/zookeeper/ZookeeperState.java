package org.example.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class ZookeeperState {
    public static void main(String[] args) throws InterruptedException, IOException {
        String connectionString = "datagov-kfk08.deltaww.com:2181";
        int sessionTimeout = 5000;
        ZooKeeper zk = new ZooKeeper(connectionString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println(event.toString());
            }
        });
        ZooKeeper.States states = zk.getState();
        System.out.println("ZooKeeper State: " + states);

        zk.close();
    }
}
