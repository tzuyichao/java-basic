package string;

import java.util.stream.IntStream;

public class Permutation {
    public static void permutationAndPrint(String str) {
        permutationAndPrint("", str);
    }

    private static void permutationAndPrint(String prefix, String str) {
        int n = str.length();
        if(n == 0) {
            System.out.print(prefix + " ");
        } else {
            for(int i=0; i<n; i++) {
                permutationAndPrint(prefix + str.charAt(i), str.substring(i+1, n) + str.substring(0, i));
            }
        }
    }

    public static void permutationAndPrint2(String str) {
        permutationAndPrint2("", str);
    }

    /**
     * Java 8
     * @param prefix
     * @param str
     */
    private static void permutationAndPrint2(String prefix, String str) {
        int n = str.length();
        if(n == 0) {
            System.out.print(prefix + " ");
        } else {
            IntStream.range(0, n)
                    .parallel()
                    .forEach(i -> permutationAndPrint2(prefix + str.charAt(i), str.substring(i + 1, n) + str.substring(0, i)));
        }
    }
}
