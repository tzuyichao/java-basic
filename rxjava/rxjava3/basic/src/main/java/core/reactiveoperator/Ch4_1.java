package core.reactiveoperator;

import io.reactivex.rxjava3.core.Observable;

public class Ch4_1 {
    public static void main(String[] args) {
        Observable<String> source1 = Observable.just("Alpha", "Beta");
        Observable<String> source2 = Observable.just("Zeta", "Eta");
        Observable.merge(source1, source2)
                .subscribe(i -> System.out.println("RECEIVED: " + i));
    }
}
