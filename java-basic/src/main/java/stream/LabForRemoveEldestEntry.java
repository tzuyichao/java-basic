package stream;

import java.util.LinkedHashMap;
import java.util.Map;

public class LabForRemoveEldestEntry {
    public static void main(String[] args) {
        Map<String, String> map = new FixedRemoveEldestMap<>(3);
        map.put("a", "123");
        map.put("b", "456");
        map.put("c", "789");
        System.out.println(map);
        map.put("d", "0");
        System.out.println(map);
    }
}

final class FixedRemoveEldestMap<K, V> extends LinkedHashMap<K, V> {
    private int limit;

    public FixedRemoveEldestMap(int limit) {
        this.limit = limit;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        if(size() > limit) {
            return true;
        }
        return false;
    }
}
