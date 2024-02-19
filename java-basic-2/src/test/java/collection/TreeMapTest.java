package collection;

import org.junit.jupiter.api.Test;

import java.util.TreeMap;

public class TreeMapTest {
    @Test
    void test_default_ordering() {
        TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("1", "one");
        treeMap.put("2", "two");
        treeMap.put("10", "ten");

        System.out.println(treeMap);
    }
}
