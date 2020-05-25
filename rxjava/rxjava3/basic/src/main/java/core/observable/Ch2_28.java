package core.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

public class Ch2_28 {
    public static void main(String[] args) {
        Single.just("Hello!")
                .map(String::length)
                .subscribe(l -> System.out.println("RECEIVE: " + l),
                        Throwable::printStackTrace);
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma");
        Single<String> first = source.first("Nil");
        first.subscribe(s -> System.out.println("RECEIVE: " + s));

        Observable<String> empty = Observable.empty();
        Single<String> firstFromEmpty = empty.first("Nil");
        firstFromEmpty.subscribe(s -> System.out.println("RECEIVE: " + s));
    }
}
