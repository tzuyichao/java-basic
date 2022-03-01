package brute;

import java.util.*;

/**
 * 146. LRU Cache
 * https://leetcode.com/problems/lru-cache/
 *
 * Time Limit Exceeded
 *
 */
public class LRUCache {
    public static final int NOT_EXIST = -1;
    private Map<Integer, Integer> store;
    private int capacity;
    private LinkedList<Map.Entry<Integer, Integer>> entries;

    public LRUCache(int capacity) {
        store = new HashMap<>();
        this.capacity = capacity;
        entries = new LinkedList<>();
    }

    public int get(int key) {
        if (store.containsKey(key)) {
            for(int i=0; i<entries.size(); i++) {
                Map.Entry entry = entries.get(i);
                if(entry.getKey().equals(key)) {
                    entries.remove(i);
                    entries.addFirst(entry);
                }
            }
            return store.get(key);
        } else {
            return NOT_EXIST;
        }
    }

    public void put(int key, int value) {
        if(!store.containsKey(key) && store.size() >= capacity) {
            Map.Entry entry = entries.removeLast();
            store.remove(entry.getKey());
            store.put(key, value);
            Map.Entry newEntry = new AbstractMap.SimpleEntry(key, value);
            entries.addFirst(newEntry);
        } else {
            if(!store.containsKey(key)) {
                Map.Entry newEntry = new AbstractMap.SimpleEntry(key, value);
                entries.addFirst(newEntry);
            } else {
                for(int i=0; i<entries.size(); i++) {
                    Map.Entry entry = entries.get(i);
                    if(entry.getKey().equals(key)) {
                        entries.remove(i);
                        entries.addFirst(entry);
                    }
                }
            }
            store.put(key, value);
        }
    }
}
