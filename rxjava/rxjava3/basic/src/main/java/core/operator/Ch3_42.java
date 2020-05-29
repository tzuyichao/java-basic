package core.operator;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_42 {
    public static void main(String[] args) {
        System.out.println("V0");
        Observable<Integer> source = Observable.just(5, 2, 4, 0, 3);
        source.map(i -> 10 / i)
                .subscribe(s -> System.out.println("V0 RECEIVED: " + s),
                        e -> System.err.println("V0 RECEIVED ERROR: " + e));
        System.out.println("onErrorReturnItem()");
        source.map(i -> 10/i)
                .onErrorReturnItem(-1)
                .subscribe(s -> System.out.println("onErrorReturnItem RECEIVED: " + s),
                        e -> System.err.println("onErrorReturnItem RECEIVED ERROR: " + e));
        System.out.println("onErrorReturn()");
        source.map(i -> 10/i)
                .onErrorReturn(e -> e instanceof ArithmeticException?-1:0)
                .subscribe(s -> System.out.println("onErrorReturn RECEIVED: " + s),
                        e -> System.err.println("onErrorReturn RECEIVED ERROR: " + e));
        source.map(i -> {
            try {
                return 10/i;
            } catch (ArithmeticException e) {
                return -1;
            }
        }).subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
