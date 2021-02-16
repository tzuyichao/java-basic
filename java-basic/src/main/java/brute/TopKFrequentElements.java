package brute;

import java.util.*;

/**
 * = 347
 * v1:
 * Runtime: 9 ms, faster than 86.08% of Java online submissions for Top K Frequent Elements.
 * Memory Usage: 41.7 MB, less than 58.82% of Java online submissions for Top K Frequent Elements.
 */
public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        if(nums.length == 1) {
            return nums;
        }
        Arrays.sort(nums);
        TreeMap<Integer, List<Integer>> store = new TreeMap<>(Collections.reverseOrder());
        int current = nums[0];
        int count = 1;
        for(int i=1; i<nums.length; i++) {
            if(nums[i] == current) {
                count+=1;
            } else {
                if(store.containsKey(count)) {
                    store.get(count).add(current);
                } else {
                    List<Integer> data = new ArrayList<>();
                    data.add(current);
                    store.put(count, data);
                }
                current = nums[i];
                count = 1;
            }
        }

        if(store.containsKey(count)) {
            store.get(count).add(current);
        } else {
            List<Integer> data = new ArrayList<>();
            data.add(current);
            store.put(count, data);
        }
        System.out.println(store);
        List<Integer> collector = new ArrayList<>();
        count = 0;
        for (Iterator<Integer> it = store.navigableKeySet().iterator(); it.hasNext(); ) {
            Integer key = it.next();
            for(Integer item : store.get(key)) {
                if(count < k) {
                    collector.add(item);
                    count += 1;
                } else {
                    break;
                }
            }
        }
        int[] r = new int[collector.size()];
        for(int i=0; i<r.length; i++) {
            r[i] = collector.get(i);
        }
        return r;
    }
}
