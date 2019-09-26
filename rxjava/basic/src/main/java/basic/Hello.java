package basic;

import io.reactivex.Flowable;

public class Hello {
    public static void hello(String... args) {
        Flowable.fromArray(args).subscribe(str -> {
            System.out.println("Hello " + str + "!");
        });
    }

    public static void main(String[] args) {
        String[] data = new String[] {"Ben", "George"};
        hello(data);
    }
}
