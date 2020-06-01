package core.reactiveoperator;

import io.reactivex.rxjava3.core.Observable;

public class Ch4_8 {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma")
                .flatMap(s -> Observable.fromArray(s.split("")),
                        (s, r) -> s + "-" + r)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
