package collection;

import java.util.Objects;
import java.util.stream.IntStream;

public class MyArrays {
    public static int findIndexOfElement(int[] arr, int toFind) {
        Objects.requireNonNull(arr, "arr can't be null");
        return IntStream.range(0, arr.length)
                .filter(i -> toFind == arr[i])
                .findFirst()
                .orElse(-1);
    }
}
