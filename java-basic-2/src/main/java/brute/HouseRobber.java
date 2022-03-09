package brute;

import java.util.Arrays;

/**
 * 198. House Robber
 * https://leetcode.com/problems/house-robber/
 *
 * Runtime: 2 ms, faster than 20.41% of Java online submissions for House Robber.
 * Memory Usage: 42 MB, less than 10.08% of Java online submissions for House Robber.
 */
public class HouseRobber {
    private int max(int[] nums, int idx) {
        int res = nums[0];
        for(int i=1; i<idx; i++) {
            if(nums[i] > res) {
                res = nums[i];
            }
        }
        return res;
    }

    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 1) {
            return nums[0];
        }
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = nums[1];
        for(int i=2; i<n; i++) {
            dp[i] = nums[i] + max(dp, i-1);
        }
        return Arrays.stream(dp).max().getAsInt();
    }
}
