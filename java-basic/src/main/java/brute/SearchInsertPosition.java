package brute;

/**
 * = 35
 */
public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        if(nums.length == 1) {
            if(nums[0] >= target) {
                return 0;
            } else {
                return 1;
            }
        }
        for(int i=0; i<nums.length; i++) {
            if(nums[i] >= target) {
                return i;
            }
        }
        return nums.length;
    }
}
