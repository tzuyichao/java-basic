package org.example.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

public class TopicInfo {
    private static final String ZOOKEEPER_CONNECT = "zkserver:2181";
    private static final int SESSION_TIMEOUT_MS = 10000;

    public static void main(String[] args) {
        try (ZooKeeper zooKeeper = new ZooKeeper(ZOOKEEPER_CONNECT, SESSION_TIMEOUT_MS, null)) {

            String topicPath = "/brokers/topics";
            List<String> topics = zooKeeper.getChildren(topicPath, false);
            for(String topic : topics) {
                System.out.println(topic);
                byte[] topicData = zooKeeper.getData(topicPath + "/" + topic, false, null);
                String topicDataString = new String(topicData);
                System.out.println("Topic Data: " + topicDataString);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (KeeperException e) {
            throw new RuntimeException(e);
        }
    }
}
