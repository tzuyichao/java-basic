package core.operator;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_34 {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma")
                .toMap(s -> s.charAt(0))
                .subscribe(s -> System.out.println("RECEIVED: " + s));

        Observable.just("Alpha", "Beta", "Apple", "Gamma")
                .toMap(s -> s.charAt(0))
                .subscribe(s -> System.out.println("RECEIVED: " + s));

        Observable.just("Alpha", "Beta", "Apple", "Gamma")
                .toMultimap(s -> s.charAt(0))
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
