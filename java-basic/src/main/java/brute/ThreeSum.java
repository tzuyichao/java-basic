package brute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums.length < 3) {
            return Collections.EMPTY_LIST;
        }
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for(int i=0; i<nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        for(int first=0; first<nums.length-2; first++) {
            int second = first+1;
            int third = nums.length - 1;
            while(second < third) {
                int sum = nums[first] + nums[second] + nums[third];
                if(sum == 0) {
                    List<Integer> r = new ArrayList<>();
                    r.add(nums[first]);
                    r.add(nums[second]);
                    r.add(nums[third]);
                    if(!result.contains(r)) {
                        result.add(r);
                    }
                    second++;
                    third--;
                } else if(sum > 0) {
                    third--;
                } else if(sum < 0) {
                    second++;
                }
            }
        }
        return result;
    }
}
