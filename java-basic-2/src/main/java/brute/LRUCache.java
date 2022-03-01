package brute;

import java.util.*;

/**
 * 146. LRU Cache
 * https://leetcode.com/problems/lru-cache/
 *
 * Time Limit Exceeded
 *
 * Time Limit Exceeded
 *
 */
public class LRUCache {
    public static final int NOT_EXIST = -1;
    private Map<Integer, Map.Entry<Integer, Integer>> store;
    private int capacity;
    private LinkedList<Map.Entry<Integer, Integer>> entries;

    public LRUCache(int capacity) {
        store = new HashMap<>();
        this.capacity = capacity;
        entries = new LinkedList<>();
    }

    public int get(int key) {
        if (store.containsKey(key)) {
            Map.Entry<Integer, Integer> entry = store.get(key);
            if(!entry.equals(entries.getFirst())) {
                entries.remove(entry);
                entries.addFirst(entry);
            }
            return entry.getValue();
        } else {
            return NOT_EXIST;
        }
    }

    public void put(int key, int value) {
        if(!store.containsKey(key) && store.size() >= capacity) {
            Map.Entry entry = entries.removeLast();
            store.remove(entry.getKey());
            Map.Entry newEntry = new AbstractMap.SimpleEntry(key, value);
            entries.addFirst(newEntry);
            store.put(key, newEntry);
        } else {
            if(!store.containsKey(key)) {
                Map.Entry newEntry = new AbstractMap.SimpleEntry(key, value);
                entries.addFirst(newEntry);
                store.put(key, newEntry);
            } else {
                Map.Entry entry = store.get(key);
                entry.setValue(value);
                if(!entry.equals(entries.getFirst())) {
                    entries.remove(entry);
                    entries.addFirst(entry);
                }
                store.put(key, entry);
            }
        }
    }
}
