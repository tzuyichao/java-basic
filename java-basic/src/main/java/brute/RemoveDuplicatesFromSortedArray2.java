package brute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * = 80
 */
public class RemoveDuplicatesFromSortedArray2 {
    public int removeDuplicates(int[] nums) {
        if(null == nums || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> counter = new HashMap<>();
        for(int j = 0; j < nums.length; j++) {
            if(counter.containsKey(nums[j])) {
                counter.put(nums[j], counter.get(nums[j])+1);
            } else {
                counter.put(nums[j], 1);
            }
        }
        List<Integer> keys = counter.keySet().stream().sorted().collect(Collectors.toList());
        int idx = 0;
        for(Integer key: keys) {
            int len = counter.get(key) >= 2?2:1;
            for(int i=0; i<len; i++) {
                nums[idx+i] = key;
            }
            idx += len;
        }
        return idx;
    }
}
