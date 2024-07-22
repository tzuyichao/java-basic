package brute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectionOfTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map_nums1 = new HashMap<>();
        Map<Integer, Integer> map_nums2 = new HashMap<>();

        for(int num : nums1) {
            map_nums1.merge(num, 1, Integer::sum);
        }
        for(int num : nums2) {
            map_nums2.merge(num, 1, Integer::sum);
        }
        List<Integer> res = new ArrayList<>();

        map_nums1.entrySet().forEach(entry -> {
            if(map_nums2.containsKey(entry.getKey())) {
                int count = Math.min(entry.getValue(), map_nums2.get(entry.getKey()));
                for(int i=0; i<count; i++) {
                    res.add(entry.getKey());
                }
            }
        });

        return res.stream().mapToInt(Integer::intValue).toArray();
    }
}
