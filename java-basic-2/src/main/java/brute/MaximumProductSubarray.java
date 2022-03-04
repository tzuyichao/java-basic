package brute;

/**
 * 152. Maximum Product Subarray
 * https://leetcode.com/problems/maximum-product-subarray/
 *
 * Runtime: 238 ms, faster than 5.65% of Java online submissions for Maximum Product Subarray.
 * Memory Usage: 42.5 MB, less than 49.89% of Java online submissions for Maximum Product Subarray.
 */
public class MaximumProductSubarray {
    public int maxProduct(int[] nums) {
        int result = nums[0];
        int n = nums.length;

        for(int i=0; i<n; i++) {
            int m = nums[i];
            for(int j=i+1; j<n; j++) {
                result = Math.max(result, m);
                m *= nums[j];
            }
            result = Math.max(result, m);
        }

        return result;
    }
}
