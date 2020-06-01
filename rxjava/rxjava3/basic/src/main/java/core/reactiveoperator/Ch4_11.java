package core.reactiveoperator;

import io.reactivex.rxjava3.core.Observable;

public class Ch4_11 {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma");
        source.concatMap(s -> {
            System.out.println("GET: " + s);
            return Observable.fromArray(s.split(""));
        })
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
