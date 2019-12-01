package server;

import com.hazelcast.config.Config;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;
import java.util.Queue;

public class HazelcastServerMaster {
    public static void main(String[] args) {
        Config config = new Config();
        GroupConfig groupConfig = new GroupConfig("demo", "demo-pass");
        config.setGroupConfig(groupConfig);
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);

        Map<Integer, String> clusterMap = hazelcastInstance.getMap("MyMap");
        clusterMap.put(1, "Hello hazelcast map");

        Queue<String> clusterQuque = hazelcastInstance.getQueue("MyQueue");
        clusterQuque.offer("Hello hazelcast");
        clusterQuque.offer("Hello hazelcast queue!");
    }
}
