package brute;

public class MaximumSubArray {
    private int sumFromPosWithLength(int[] nums, int pos, int length) {
        int sum = 0;
        for(int i = pos; i<pos+length; i++) {
            sum += nums[i];
        }
        return sum;
    }


    public int maxSubArray(int[] nums) {
        int max = sumFromPosWithLength(nums, 0, nums.length);
        for(int i = 0; i < nums.length; i++) {
            for (int l = nums.length-i; l > 0; l--) {
                if(nums[i+l-1] > 0 || nums[i+l-1] > max) {
                    int step = sumFromPosWithLength(nums, i, l);
                    if(step > max) {
                        max = step;
                    }
                } else {
                    continue;
                }
            }
        }
        return max;
    }
}
