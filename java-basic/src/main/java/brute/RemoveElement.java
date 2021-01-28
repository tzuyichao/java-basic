package brute;

/**
 * = 27
 */
public class RemoveElement {
    public int removeElement(int[] nums, int val) {
        int indexLast = nums.length - 1;
        for(int i=nums.length-1; i>=0; i--) {
            if(nums[i] == val) {
                if(indexLast > i) {
                    int temp = nums[indexLast];
                    nums[indexLast] = nums[i];
                    nums[i] = temp;
                }
                indexLast -= 1;
            }
        }
        return indexLast+1;
    }
}
