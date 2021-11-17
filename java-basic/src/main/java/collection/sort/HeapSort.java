package collection.sort;

import java.util.Arrays;

public class HeapSort {
    public static void heapSort(int[] arr) {
        int n = arr.length;
        System.out.println("Source:" + Arrays.toString(arr));
        buildHeap(arr, n);
        System.out.println("Build Heap" + Arrays.toString(arr));
        while(n > 1) {
            swap(arr, 0, n-1);
            n--;
            heapify(arr, n, 0);
        }
    }

    private static void buildHeap(int[] arr, int n) {
        for(int i=arr.length/2; i>=0; i--) {
            heapify(arr, n, i);
        }
    }

    private static void heapify(int[] arr, int n, int i) {
        int left = i*2+1;
        int right = i*2+2;
        int greater;

        if(left < n && arr[left]>arr[i]) {
            greater = left;
        } else {
            greater = i;
        }

        if (right < n && arr[right] > arr[greater]) {
            greater = right;
        }

        if (greater != i) {
            swap(arr, i, greater);
            heapify(arr, n, greater);
        }
    }

    private static void swap(int[] arr, int x, int y) {
        int temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 5, 2, 7, 1};
        heapSort(arr);
        System.out.println("Sorted: " + Arrays.toString(arr));
    }
}
