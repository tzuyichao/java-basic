package javassist.basic;

import java.util.Arrays;

public class Run {
    public static void main(String[] args) {
        System.out.println("Run#main()");
        Arrays.stream(args).forEach(System.out::println);
    }
}
