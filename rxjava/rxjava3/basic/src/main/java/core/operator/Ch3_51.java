package core.operator;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_51 {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma")
                .doAfterNext(s -> System.out.println("After: " + s))
                .map(String::length)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
