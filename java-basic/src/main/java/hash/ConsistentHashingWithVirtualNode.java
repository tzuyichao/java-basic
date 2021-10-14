package hash;

import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * https://programmer.help/blogs/consistency-hash-algorithm-principle-and-java-implementation.html
 *
 */
public class ConsistentHashingWithVirtualNode {
    private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111"};
    private static List<String> realNodes = new LinkedList<>();
    private static SortedMap<Integer, String> virtualNodes = new TreeMap<>();
    private static final int VIRTUAL_NODES = 5;

    static {
        for(String server: servers) {
            realNodes.add(server);
        }

        for(String node: realNodes) {
            for(int i=0; i<VIRTUAL_NODES; i++) {
                String virtualNodeName = node + "&&VN" + i;
                int hash = getHash(virtualNodeName);
                System.out.println("Virtual Node [" + virtualNodeName + "] be added, hash value is " + hash);
                virtualNodes.put(hash, virtualNodeName);
            }
        }
    }

    private static Integer getHash(String key) {
        final int p = 16777619;
        int hash = (int) 2166136261L;
        for (int i = 0; i < key.length(); i++)
            hash = (hash ^ key.charAt(i)) * p;
        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        // If the calculated value is negative, take its absolute value.
        if (hash < 0)
            hash = Math.abs(hash);
        return hash;
    }

    private static String getServer(String key) {
        int hash = getHash(key);
        SortedMap<Integer, String> subMap = virtualNodes.tailMap(hash);
        String virtualNode;
        if(subMap.isEmpty()) {
            Integer i = virtualNodes.firstKey();
            virtualNode = virtualNodes.get(i);
        } else {
            Integer i = subMap.firstKey();
            virtualNode = virtualNodes.get(i);
        }
        if(virtualNode != null && !"".equals(virtualNode)) {
            return virtualNode.substring(0, virtualNode.indexOf("&&"));
        }
        return null;
    }

    public static void main(String[] args) {
        String[] keys = {"sunlight", "Moon", "Star"};
        for(String key: keys) {
            System.out.println("[" + key + "] of hash value is " + getHash(key) + ", route to Node [" + getServer(key) + "]");
        }
    }
}
