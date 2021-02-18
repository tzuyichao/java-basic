package brute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * = 31
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        if(nums.length == 1) {
            return;
        }
        int max = -1;
        List<Integer> maxPos = new ArrayList<>();

        for(int i=0; i<nums.length; i++) {
            if(nums[i] > max) {
                max = nums[i];
                maxPos.clear();
                maxPos.add(i);
            }
            if(nums[i] == max) {
                maxPos.add(i);
            }
        }

        if(maxPos.contains(0)) {
            Arrays.sort(nums);
        }

        for(int i=nums.length-2; i>0; i--) {
            if (nums[nums.length - 1] > nums[i]) {
                int temp = nums[nums.length - 1];
                int[] move = Arrays.copyOfRange(nums, i, nums.length-1);

                nums[i] = temp;
                int idx = 0;
                for(int j=i+1; j<nums.length; j++) {
                    nums[j] = move[idx];
                    idx+=1;
                }
            }
        }
    }
}
