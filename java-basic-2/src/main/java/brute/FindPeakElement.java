package brute;

/**
 * 162. Find Peak Element
 * https://leetcode.com/problems/find-peak-element/
 *
 * Runtime: 1 ms, faster than 9.84% of Java online submissions for Find Peak Element.
 * Memory Usage: 42.8 MB, less than 33.50% of Java online submissions for Find Peak Element.
 *
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Find Peak Element.
 * Memory Usage: 43.3 MB, less than 25.82% of Java online submissions for Find Peak Element.
 */
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        var low = 0;
        var high = nums.length-1;
        while(low < high) {
            int mid1 = (low + high)/2;
            int mid2 = mid1+1;
            if(nums[mid1] < nums[mid2]) {
                low = mid2;
            } else {
                high = mid1;
            }
        }
        return low;
    }
}
