package lambda;

import java.util.Arrays;

public class LambdaTest {
    public static void main(String[] args) {
        Runnable r = () -> System.out.println(Arrays.toString(args));
        r.run();
    }
}
