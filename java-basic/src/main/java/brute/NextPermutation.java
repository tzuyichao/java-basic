package brute;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * = 31
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        if(n == 0 || n == 1) {
            return;
        }
        for(int i=1; i<nums.length; i++) {
            if(nums[i-1] <nums[i]) {
                n = i;
            }
        }
        if(n == nums.length) {
            for(int i=0; i*2+1<nums.length; i++) {
                int temp = nums[i];
                nums[i] = nums[nums.length-i-1];
                nums[nums.length - i -1] = temp;
            }
        } else if(n+1 == nums.length) {
            int temp = nums[n-1];
            nums[n-1] = nums[n];
            nums[n] = temp;
        } else {
            int base = nums[n-1];
            int minValue = nums[n];
            int minIndex = n;
            for(int i=n+1; i<nums.length; i++) {
                if(nums[i] > base && nums[i] <= minValue) {
                    minValue = nums[i];
                    minIndex = i;
                }
            }
            int temp = nums[n-1];
            nums[n-1] = nums[minIndex];
            nums[minIndex] = temp;

            int length = (nums.length - n)/2;
            for(int i=0; i<length; i++) {
                temp = nums[i+n];
                nums[i+n] = nums[nums.length-i-1];
                nums[nums.length-i-1] = temp;
            }
        }
    }
}
