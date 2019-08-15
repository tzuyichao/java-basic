package util;

import java.util.Arrays;
import java.util.stream.Stream;

public class MethodReferenceLab1 {
    private static int compare(Integer a, Integer b) {
        return a.compareTo(b);
    }

    public static void main(String[] args) {
        Integer[] test = new Integer[] {5, 3, 6, 1};
        Arrays.sort(test, MethodReferenceLab1::compare);
        Stream.of(test).forEach(System.out::println);
    }
}
