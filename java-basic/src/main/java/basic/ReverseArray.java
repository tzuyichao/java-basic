package basic;

import java.util.Arrays;
import java.util.stream.Stream;

public class ReverseArray {
    static int[] reverseArray(int[] a) {
        int[] result = new int[a.length];
        for(int i=0; i<a.length; i++) {
            result[i] = a[a.length-1-i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 4, 3, 2};
        int[] result = reverseArray(arr);
        for(int i = 0; i<result.length; i++) {
            System.out.println(result[i]);
        }
    }
}
