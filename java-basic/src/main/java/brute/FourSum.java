package brute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * = 18
 */
public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if(nums.length < 4) {
            return Collections.EMPTY_LIST;
        }
        if(nums.length == 4) {
            int sum = 0;
            for(int i=0; i<nums.length; i++) {
                sum += nums[i];
            }
            if(sum == target) {
                return List.of(List.of(nums[0], nums[1], nums[2], nums[3]));
            } else {
                return Collections.EMPTY_LIST;
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for(int first=0; first<nums.length-3; first++) {
            for(int second = first + 1; second<nums.length-2; second++) {
                for (int third = second + 1; third < nums.length - 1; third++) {
                    for(int four = nums.length-1; four > third; four--) {
                        int sum = nums[first] + nums[second] + nums[third] + nums[four];
                        if(sum == target) {
                            result.add(List.of(nums[first], nums[second], nums[third], nums[four]).stream().sorted().collect(Collectors.toList()));
                        }
                    }
                }
            }
        }

        return result.stream().distinct().collect(Collectors.toList());
    }
}
