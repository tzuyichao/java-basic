package brute;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * = 41
 * v1:
 * Runtime: 5 ms, faster than 7.47% of Java online submissions for First Missing Positive.
 * Memory Usage: 37 MB, less than 34.97% of Java online submissions for First Missing Positive.
 */
public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        if(nums.length == 0) {
            return 1;
        }
        List<Integer> positivePart = new ArrayList<>();
        boolean oneFound = false;
        for(int i=0; i<nums.length; i++) {
            if(nums[i] > 0) {
                if(nums[i] == 1) {
                    oneFound = true;
                }
                positivePart.add(nums[i]);
            }
        }
        if(oneFound == false) {
            return 1;
        }
        positivePart = positivePart.stream().distinct().sorted().collect(Collectors.toList());
        int pre = positivePart.get(0);
        for(int i=1; i<positivePart.size(); i++) {
            int current = positivePart.get(i);
            if(current == pre+1) {
                pre = current;
            } else {
                return pre+1;
            }
        }
        return pre+1;
    }
}
