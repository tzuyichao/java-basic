package brute;

/**
 * = 26
 */
public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        if(null == nums || nums.length == 0) {
            return 0;
        }
        int previousValue = nums[nums.length-1];
        int lastIndex = nums.length-1;
        for(int i=nums.length-2; i>=0; i--) {
            if(nums[i] == previousValue) {
                for(int j=i+1; j<=lastIndex; j++) {
                    nums[j-1] = nums[j];
                }
                lastIndex -= 1;
            }
            previousValue = nums[i];
        }
        return lastIndex+1;
    }
}
