package brute;

public class MaximumSubArray {
    private int sumFromPosWithLength(int[] nums, int pos, int length) {
        int sum = 0;
        for(int i = pos; i<pos+length; i++) {
            sum += nums[i];
        }
        return sum;
    }

    private int max(int[] nums, int length) {
        int max = sumFromPosWithLength(nums, 0, length);
        for(int i=1; i<nums.length-length+1; i++) {
            int step = sumFromPosWithLength(nums, i, length);
            if(step > max) {
                max = step;
            }
        }
        return max;
    }

    public int maxSubArray(int[] nums) {
        int max = sumFromPosWithLength(nums, 0, nums.length);
        for(int l=1; l<nums.length; l++) {
            int step = max(nums, l);
            if(step > max) {
                max = step;
            }
        }
        return max;
    }
}
