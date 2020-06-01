package core.reactiveoperator;

import io.reactivex.rxjava3.core.Observable;

public class Ch4_5 {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma")
                .flatMap(s -> {
                    System.out.println(s);
                    return Observable.fromArray(s.split(""));
                })
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
