package core.operator;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_25 {
    public static void main(String[] args) {
        Observable.just(5, 4, 7, 11, 2, 14)
                .all(i -> i<10)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
