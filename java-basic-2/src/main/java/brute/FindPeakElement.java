package brute;

/**
 * 162. Find Peak Element
 * https://leetcode.com/problems/find-peak-element/
 *
 * Runtime: 1 ms, faster than 9.84% of Java online submissions for Find Peak Element.
 * Memory Usage: 42.8 MB, less than 33.50% of Java online submissions for Find Peak Element.
 */
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        var n = nums.length;
        if(n == 1) {
            return 0;
        }

        for(int i=0; i<n; i++) {
            if(i == 0) {
                if(nums[i+1] - nums[i] < 0) {
                    return i;
                }
            } else if(i == n-1) {
                if(nums[i] - nums[i-1] > 0) {
                    return i;
                }
            } else {
                if ((nums[i] - nums[i - 1]) * (nums[i + 1] - nums[i]) < 0) {
                    return i;
                }
            }
        }

        return 0;
    }
}
