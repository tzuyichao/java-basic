package brute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * = 219
 */
public class ContainsDuplicate2 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if(nums.length == 0 || nums.length == 1) {
            return false;
        }
        Map<Integer, List<Integer>> position = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            if(position.containsKey(nums[i])) {
                for(int pos: position.get(nums[i])) {
                    if(i - pos <= k) {
                        return true;
                    }
                }
                position.get(nums[i]).add(i);
            } else {
                List<Integer> posDetails = new ArrayList<>();
                posDetails.add(i);
                position.put(nums[i], posDetails);
            }
        }
        return false;
    }
}
