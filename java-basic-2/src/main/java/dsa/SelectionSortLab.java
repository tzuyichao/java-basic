package dsa;

public class SelectionSortLab {
    public static void selectionSort(int[] arr) {
        for(int i=0; i<arr.length-1; i++) {
            int idx = i;
            for(int j=i+1; j<arr.length; j++) {
                if(arr[i] < arr[idx]) {
                    idx = j;
                }
            }
            int small = arr[idx];
            arr[idx] = arr[i];
            arr[i] = small;
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 25, 12, 22, 11};
        selectionSort(arr);
        for (int j : arr) {
            System.out.print(STR."\{j} ");
        }
    }
}
