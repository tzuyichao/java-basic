package core.operator;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_37 {
    public static void main(String[] args) {
        Observable.just("Alpha", "Gamma", "Beta")
                .toMap(String::length)
                .subscribe(s -> System.out.println("RECEIVED: " + s));

        Observable.just("Alpha", "Gamma", "Beta")
                .toMultimap(String::length)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
