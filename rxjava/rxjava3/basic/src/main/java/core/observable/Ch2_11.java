package core.observable;

import io.reactivex.rxjava3.core.Observable;

/**
 * Cold Observable
 *
 */
public class Ch2_11 {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma");
        source.subscribe(s -> System.out.println("Observer 1: " + s));
        source.subscribe(s -> System.out.println("Observer 2: " + s));
    }
}
