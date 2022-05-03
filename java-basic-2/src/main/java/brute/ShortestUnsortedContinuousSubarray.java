package brute;

import java.util.Arrays;

/**
 * 581. Shortest Unsorted Continuous Subarray
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
 *
 * Runtime: 11 ms, faster than 28.13% of Java online submissions for Shortest Unsorted Continuous Subarray.
 * Memory Usage: 53.7 MB, less than 66.12% of Java online submissions for Shortest Unsorted Continuous Subarray.
 */
public class ShortestUnsortedContinuousSubarray {
    public int findUnsortedSubarray(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] sorted = new int[n];
        System.arraycopy(nums, 0, sorted, 0, n);
        Arrays.sort(sorted);
        int h = 0;
        int t = n - 1;
        while(h < n) {
            if(nums[h] != sorted[h]) {
                break;
            }
            h++;
        }
        while(t >= 0) {
            if(nums[t] != sorted[t]) {
                break;
            }
            t--;
        }

        int res=  t - h + 1;
        return res>0?res:0;
    }
}
