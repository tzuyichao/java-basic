package brute;

/**
 * 410. Split Array Largest Sum
 * https://leetcode.com/problems/split-array-largest-sum/
 *
 * Runtime: 1 ms, faster than 84.23% of Java online submissions for Split Array Largest Sum.
 * Memory Usage: 42.2 MB, less than 25.26% of Java online submissions for Split Array Largest Sum.
 */
public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int m) {
        int max = 0;
        int sum = 0;
        for(int num: nums) {
            max = Math.max(max, num);
            sum += num;
        }
        while(max <= sum) {
            int mid = max + ((sum-max)>>1);
            if(valid_partition(mid, nums, m)) {
                sum = mid - 1;
            } else {
                max = mid + 1;
            }
        }
        return max;
    }

    private boolean valid_partition(int target, int[] nums, int m) {
        int count = 1;
        int total = 0;
        for(int num: nums) {
            total += num;
            if(total > target) {
                count += 1;
                total = num;
                if(count > m) {
                    return false;
                }
            }
        }
        return true;
    }
}
