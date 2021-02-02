package brute;

/**
 * = 268
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = (1+n)*n/2;
        int missingNumber = sum;
        for(int i=0; i<nums.length; i++) {
            missingNumber -= nums[i];
        }
        return missingNumber;
    }
}
