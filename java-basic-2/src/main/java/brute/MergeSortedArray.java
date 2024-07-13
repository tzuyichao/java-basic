package brute;

public class MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int len = nums1.length;
        int[] data1 = new int[len];
        for(int i=0; i<len; i++) {
            data1[i] = nums1[i];
        }
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
