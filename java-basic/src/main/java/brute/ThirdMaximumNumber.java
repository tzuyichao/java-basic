package brute;

public class ThirdMaximumNumber {
    public int thirdMax(int[] nums) {
        int firstIdx = 0;
        int secondIdx = -1;
        int thirdIdx = -1;

        for(int i=1; i<nums.length; i++) {
            if(nums[i] == nums[firstIdx]) {
                continue;
            }
            if(secondIdx != -1 && nums[i] == nums[secondIdx]) {
                continue;
            }
            if(thirdIdx != -1 && nums[i] == nums[thirdIdx]) {
                continue;
            }
            if(nums[i] > nums[firstIdx]) {
                thirdIdx = secondIdx;
                secondIdx = firstIdx;
                firstIdx = i;
            } else if(secondIdx == -1 || nums[i] > nums[secondIdx]) {
                thirdIdx = secondIdx;
                secondIdx = i;
            } else if(thirdIdx == -1 || nums[i] > nums[thirdIdx]) {
                thirdIdx = i;
            }
        }

        if(thirdIdx != -1) {
            return nums[thirdIdx];
        } else {
            return nums[firstIdx];
        }
    }
}
