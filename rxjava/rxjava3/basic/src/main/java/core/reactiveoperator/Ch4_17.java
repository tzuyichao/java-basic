package core.reactiveoperator;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.GroupedObservable;

public class Ch4_17 {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma", "Delta", "Epsilon");
        Observable<GroupedObservable<Integer, String>> byLength = source.groupBy(s -> s.length());
        byLength.subscribe(o -> {
            System.out.println("key: " + o.getKey());
            o.toList().subscribe(s -> System.out.println(s));
        });
        byLength.flatMapSingle(grp -> grp.toList())
                .subscribe(s -> System.out.println(s.toString()));

    }
}
