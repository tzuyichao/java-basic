package brute;

import java.util.Arrays;

/**
 * 1679. Max Number of K-Sum Pairs
 * https://leetcode.com/problems/max-number-of-k-sum-pairs/
 *
 * Runtime: 20 ms, faster than 92.37% of Java online submissions for Max Number of K-Sum Pairs.
 * Memory Usage: 52.7 MB, less than 88.36% of Java online submissions for Max Number of K-Sum Pairs.
 */
public class MaxNumberOfKSumPairs {
    public int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int res = 0;

        int left = 0;
        int right = nums.length-1;
        while(left < right) {
            var sum = nums[left] + nums[right];
            if(sum == k) {
                res++;
                left++;
                right--;
            } else if(sum > k) {
                right--;
            } else {
                left++;
            }
        }

        return res;
    }
}
