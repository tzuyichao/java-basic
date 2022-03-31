package brute;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 347. Top K Frequent Elements
 * https://leetcode.com/problems/top-k-frequent-elements/
 *
 * Runtime: 7 ms, faster than 99.56% of Java online submissions for Top K Frequent Elements.
 * Memory Usage: 45.1 MB, less than 83.29% of Java online submissions for Top K Frequent Elements.
 */
public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        final int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        for(int num: nums) {
            map.put(num, map.getOrDefault(num, 0)+1);
        }
        List<Integer>[] bucket = new List[n+1];
        for(int num: map.keySet()) {
            int frq = map.get(num);
            if(bucket[frq] == null) {
                bucket[frq] = new LinkedList<>();
            }
            bucket[frq].add(num);
        }
        int[] res = new int[k];
        int idx = 0;
        for(int i=bucket.length-1; i>0 && k > 0; --i) {
            if(bucket[i] != null) {
                List<Integer> items = bucket[i];
                for(Integer item: items) {
                    if(idx < k) {
                        res[idx] = item;
                        idx+=1;
                    }
                }
            }
        }
        return res;
    }
}
