package basic;

import java.util.Arrays;
import java.util.Spliterators;

public class ArraysLab {
    public static void main(String[] args) {
        String[] arr = new String[] {"1", "2", "3", "4"};
        Arrays.spliterator(arr, 0, arr.length-1).forEachRemaining(item -> {
            System.out.println(item);
        });
        String test = "test";
        String[] result = test.split(" ");
        System.out.println(result.length);

        String test2 = "1    3";
        System.out.println(test2.replaceAll("[ ]+", " "));
    }
}
