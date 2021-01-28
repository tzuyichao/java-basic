package brute;

/**
 * = 26
 */
public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        if(null == nums || nums.length == 0) {
            return 0;
        }
        int i = 0;
        for(int j = 1; j < nums.length; j++) {
            if(nums[j] != nums[i]) {
                i += 1;
                nums[i] = nums[j];
            }
        }
        return i+1;
    }
}
