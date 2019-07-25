package server;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Map;
import java.util.Queue;

public class HazelcastServerSlave {
    public static void main(String[] args) {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        Map<Integer, String> clusterMap = hazelcastInstance.getMap("MyMap");
        Queue<String> clusterQueue = hazelcastInstance.getQueue("MyQueue");

        System.out.println("Map Value: " + clusterMap.get(1));
        System.out.println("Queue size: " + clusterQueue.size());
        System.out.println("Queue value 1: " + clusterQueue.poll());
        System.out.println("Queue value 2: " + clusterQueue.poll());
        System.out.println("Queue size: " + clusterQueue.size());
    }
}
