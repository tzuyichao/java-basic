package utils;

import java.util.Arrays;

public class ArraysSetAllLab {
    public static void main(String[] args) {
        int[] arr = new int[10];
        Arrays.setAll(arr, idx -> idx * 2);
        for(int i : arr) {
            System.out.println(i);
        }
        Arrays.parallelSetAll(arr, idx -> idx * 3);
        for(int i : arr) {
            System.out.println(i);
        }
    }
}
