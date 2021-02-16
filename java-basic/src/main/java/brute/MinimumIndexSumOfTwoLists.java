package brute;

import java.util.*;

/**
 * = 599
 * v1:
 * Runtime: 15 ms, faster than 28.73% of Java online submissions for Minimum Index Sum of Two Lists.
 * Memory Usage: 39.9 MB, less than 33.93% of Java online submissions for Minimum Index Sum of Two Lists.
 */
public class MinimumIndexSumOfTwoLists {
    public String[] findRestaurant(String[] list1, String[] list2) {
        TreeMap<Integer, List<String>> indexSum = new TreeMap<>();
        Map<String, Integer> index1 = new HashMap<>();
        Map<String, Integer> index2 = new HashMap<>();

        for(int i=0; i<list1.length; i++) {
            index1.put(list1[i], i);
        }

        for(int i=0; i<list2.length; i++) {
            index2.put(list2[i], i);
        }

        Set<String> key1 = index1.keySet();
        Set<String> key2 = index2.keySet();
        key1.retainAll(key2);

        for(String key : key1) {
            int index_sum = index1.get(key) + index2.get(key);
            if(indexSum.containsKey(index_sum)) {
                indexSum.get(index_sum).add(key);
            } else {
                List<String> keyList = new ArrayList<>();
                keyList.add(key);
                indexSum.put(index_sum, keyList);
            }
        }
        return indexSum.firstEntry().getValue().toArray(new String[0]);
    }
}
