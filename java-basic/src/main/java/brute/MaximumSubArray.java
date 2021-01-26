package brute;

public class MaximumSubArray {
    public int maxSubArray(int[] nums) {
        int best_max = nums[0];
        int current_max = nums[0];
        for(int i=1; i<nums.length; i++) {
            if(current_max + nums[i] > nums[i]) {
                current_max = current_max + nums[i];
            } else {
                current_max = nums[i];
            }
            if(current_max > best_max) {
                best_max = current_max;
            }
        }
        return best_max;
    }
}
