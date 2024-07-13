package brute;

public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len = nums1.length;
        int[] data1 = new int[len];
        System.arraycopy(nums1, 0, data1, 0, len);
        int i = 0, j = 0;
        int k = 0;
        while(i < m && j < n) {
            if(data1[i] > nums2[j]) {
                nums1[k] = nums2[j];
                j++;
            } else {
                nums1[k] = data1[i];
                i++;
            }
            k++;
        }
        while(i<m) {
            nums1[k] = data1[i];
            i++;
            k++;
        }
        while(j<n) {
            nums1[k] = nums2[j];
            j++;
            k++;
        }
    }
}
