package brute;

/**
 * leetcode 33
 */
public class SearchInRotatedSortedArray {
    public int findK(int[] nums) {
        int k;
        for(k = 0; k < nums.length; k++) {
            if(k+1 < nums.length) {
                if(nums[k+1] < nums[k]) {
                    return k+1;
                }
            }
        }
        return k;
    }

    public int[] make(int[] nums, int k) {
        int[] result = new int[nums.length];
        System.arraycopy(nums, k, result, 0, nums.length-k);
        System.arraycopy(nums, 0, result, nums.length-k, k);
        return result;
    }

    public int search(int[] nums, int target) {
        int k = findK(nums);
        int[] origin = make(nums, k);
        for(int i=0; i<origin.length; i++) {
            if(origin[i] == target) {
                return (i + k)%origin.length;
            }
        }
        return -1;
    }
}
