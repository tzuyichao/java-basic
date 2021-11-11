package basic;

public class CountingSort {
    public static void sort(int[] arr) {
        int min = arr[0];
        int max = arr[0];

        for(int elem : arr) {
            if(elem < min) {
                min = elem;
            }
            if(elem > max) {
                max = elem;
            }
        }
        int[] counts = new int[max-min+1];
        for(int elem: arr) {
            counts[elem-min]+=1;
        }
        int sortedIndex = 0;
        for(int i=0; i<counts.length; i++) {
            while(counts[i] > 0) {
                arr[sortedIndex++] = i + min;
                counts[i]--;
            }
        }
    }
}
