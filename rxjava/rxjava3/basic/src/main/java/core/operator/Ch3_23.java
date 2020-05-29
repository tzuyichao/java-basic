package core.operator;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_23 {
    public static void main(String[] args) {
        Observable.just(5, 3, 7)
                .reduce((total, i) -> total + i)
                .subscribe(s -> System.out.println("RECEIVE: " + s));
        Observable.just("Alpha", "Beta", "Gamma")
                .map(String::length)
                .reduce(10, (total, i) -> total + i)
                .subscribe(s -> System.out.println("RECEIVE: " + s));
    }
}
