package brute;

/**
 * = 88
 */
public class MergeSortedArray {
    private void insert(int[] num, int value, int pos) {
        for(int i=num.length-2; i>=pos; i--) {
            num[i+1] = num[i];
        }
        num[pos] = value;
    }

    private int posToInsert(int[] num, int size, int value) {
        for(int i=0; i<size; i++) {
            if(num[i] > value) {
                return i;
            }
        }
        return size;
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if(nums1.length == nums2.length) {
            for(int i=0; i<nums2.length; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }
        for(int i=0; i<n; i++) {
            int pos = posToInsert(nums1, m+i, nums2[i]);
            if(pos != -1) {
                insert(nums1, nums2[i], pos);
            }
        }
    }
}
