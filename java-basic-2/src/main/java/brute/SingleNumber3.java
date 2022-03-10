package brute;

import java.util.Arrays;

/**
 * 260. Single Number III
 * https://leetcode.com/problems/single-number-iii/
 * 
 */
public class SingleNumber3 {
    public int[] singleNumber(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);

        int i = 0;
        int j = 1;
        int r1 = -1;
        int r2 = -1;
        while(j < n) {
            if(nums[i] != nums[j]) {
                if(r1 == -1) {
                    r1 = i;
                } else if(r2 == -1) {
                    r2 = i;
                }
                i += 1;
                j += 1;
            } else {
                i += 2;
                j += 2;
            }
        }
        if(r1 == -1) {
            r1 = n-2;
        }
        if(r2 == -1) {
            r2 = n-1;
        }
        return new int[] {nums[r1], nums[r2]};
    }
}
