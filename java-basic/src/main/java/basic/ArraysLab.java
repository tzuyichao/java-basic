package basic;

import java.util.Arrays;
import java.util.Comparator;

public class ArraysLab {
    private static void test1() {
        Integer[] arr = new Integer[] {2, 0, 10};
        Arrays.sort(arr, Comparator.reverseOrder());
        System.out.println(Arrays.toString(arr));
    }

    private static int test2(int[] in) {
        Integer[] arr = Arrays.stream(in).boxed().toArray(Integer[]::new);
        Arrays.sort(arr, Comparator.reverseOrder());
        System.out.println(Arrays.toString(arr));
        int result = 0;
        if(arr.length == 1) {
            return result;
        } if(arr.length == 2) {
            return arr[0] -arr[1];
        } else {
            for (int i = 0; i < arr.length-1; i++) {
                result += (arr[i] - arr[i+1]);
            }
            return result;
        }
    }

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

        test1();

        System.out.println(test2(new int[]{1, 2, 10}));
    }
}
