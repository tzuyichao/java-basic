package core.operator;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_11 {
    public static void main(String[] args) {
        Observable.just(1, 1, 1, 2, 2, 3, 3, 2, 1, 1)
                .distinctUntilChanged()
                .subscribe(i -> System.out.println("RECEIVE: " + i));
        Observable.just("Alpha", "Beta", "Zeta", "Eta", "Gamma", "Delta")
                .distinctUntilChanged(String::length)
                .subscribe(s -> System.out.println("RECEIVE: " + s));
    }
}
