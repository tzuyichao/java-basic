package core.operator;

import io.reactivex.rxjava3.core.Observable;

/**
 * 永遠不會停
 */
public class Ch3_48 {
    public static void main(String[] args) {
        Observable.just(5, 2, 4, 0, 3)
                .map(i -> 10 / i)
                .retry()
                .subscribe(s -> System.out.println("RECEIVED: " + s),
                        e -> System.err.println("RECEIVED ERROR: " + e));
    }
}
