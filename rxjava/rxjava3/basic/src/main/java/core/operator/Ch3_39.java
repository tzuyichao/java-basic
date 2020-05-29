package core.operator;

import io.reactivex.rxjava3.core.Observable;

import java.util.HashSet;

public class Ch3_39 {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Beta");

        source.subscribe(s -> System.out.println("ORIGIN: " + s));

        source.collect(HashSet<String>::new, HashSet::add)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
