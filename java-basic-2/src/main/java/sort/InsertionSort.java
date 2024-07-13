package sort;

public class InsertionSort {
    public static void sort(int[] arr) {
        int len = arr.length;
        for(int i = 1; i < len; i++) {
            int val = arr[i];
            int j = i - 1;

            while(j >= 0 && val < arr[j]) {
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = val;
        }
    }
}
