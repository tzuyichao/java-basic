package brute;

/**
 * = 283
 */
public class MoveZeros {
    private void swap(int[] nums, int sourceIndex, int targetIndex) {
        int temp = nums[targetIndex];
        nums[targetIndex] = nums[sourceIndex];
        nums[sourceIndex] = temp;
    }

    public void moveZeroes(int[] nums) {
        int move = 0;
        for(int i=0; i<nums.length; i++) {
            if(nums[i] == 0) {
                move += 1;
            } else {
                swap(nums, i, i-move);
            }
        }
    }
}
