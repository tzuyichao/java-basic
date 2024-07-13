package brute;

public class MaxConsecutiveOnes {
    public int findMaxConsecutiveOnes(int[] nums) {
        int[] count = new int[nums.length];
        if(nums[0] == 1) {
            count[0] = 1;
        } else {
            count[0] = 0;
        }
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] == 1) {
                if(count[i-1] == 0) {
                    count[i] = 1;
                } else {
                    count[i] = count[i-1] + 1;
                }
            } else {
                count[i] = 0;
            }
        }
        int max = 0;
        for(int i=0; i<nums.length; i++) {
            if(count[i] > max) {
                max = count[i];
            }
        }
        return max;
    }
}
