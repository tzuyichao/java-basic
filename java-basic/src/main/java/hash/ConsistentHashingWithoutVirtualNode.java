package hash;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * https://programmer.help/blogs/consistency-hash-algorithm-principle-and-java-implementation.html
 *
 */
public class ConsistentHashingWithoutVirtualNode {
    private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111"};
    private static SortedMap<Integer, String> sortedMap = new TreeMap<>();

    static {
        for(String server: servers) {
            int hash = getHash(server);
            System.out.println("[" + server + "] join the collection, his hash value is " + hash);
            sortedMap.put(hash, server);
        }
        System.out.println();
    }

    private static String getServer(String key) {
        int hash = getHash(key);
        SortedMap<Integer, String> partial = sortedMap.tailMap(hash);
        if(partial.isEmpty()) {
            // circular
            Integer i = sortedMap.firstKey();
            return sortedMap.get(i);
        } else {
            Integer i = partial.firstKey();
            return partial.get(i);
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

    public static void main(String[] args) {
        String[] keys = {"sunlight", "Moon", "Stars"};
        for(String key: keys) {
            System.out.println("[" + key + "] of hash the value is " + getHash(key) + ", Routed to Node[" + getServer(key) + "]");
        }
    }
}
