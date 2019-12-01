package client;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;
import java.util.Queue;

public class HazelcastClientLab {
    public static void main(String[] args) {
        ClientConfig clientConfig = new ClientConfig();
        GroupConfig groupConfig = new GroupConfig("demo", "demo-pass");
        clientConfig.setGroupConfig(groupConfig);
        HazelcastInstance hazelcastInstance = HazelcastClient.newHazelcastClient(clientConfig);

        Map<Integer, String> clusterMap = hazelcastInstance.getMap("MyMap");
        Queue<String> clusterQueue = hazelcastInstance.getQueue("MyQueue");

        System.out.println("Map Value: " + clusterMap.get(1));
        System.out.println("Queue size: " + clusterQueue.size());
        System.out.println("Queue value 1: " + clusterQueue.poll());
        System.out.println("Queue value 2: " + clusterQueue.poll());
        System.out.println("Queue size: " + clusterQueue.size());
    }
}
