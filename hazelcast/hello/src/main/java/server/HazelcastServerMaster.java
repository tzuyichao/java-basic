package server;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;
import java.util.Queue;

public class HazelcastServerMaster {
    public static void main(String[] args) {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();

        Map<Integer, String> clusterMap = hazelcastInstance.getMap("MyMap");
        clusterMap.put(1, "Hello hazelcast map");

        Queue<String> clusterQuque = hazelcastInstance.getQueue("MyQueue");
        clusterQuque.offer("Hello hazelcast");
        clusterQuque.offer("Hello hazelcast queue!");
    }
}
