package collection;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.IntStream;

public class MyArrays {
    public static boolean containsElement(int[] arr, int toContain) {
        Objects.requireNonNull(arr, "arr can't be null");
        return Arrays.stream(arr)
                .anyMatch(e -> e == toContain);
    }

    public static <T> boolean containsElement(T[] arr, T toContain, Comparator<? super T> comparator) {
        for(T elem: arr) {
            if(comparator.compare(elem, toContain) == 0) {
                return true;
            }
        }
        return false;
    }

    public static int findIndexOfElement(int[] arr, int toFind) {
        Objects.requireNonNull(arr, "arr can't be null");
        return IntStream.range(0, arr.length)
                .filter(i -> toFind == arr[i])
                .findFirst()
                .orElse(-1);
    }
}
