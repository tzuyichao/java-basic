package core.observable;

import io.reactivex.rxjava3.core.Observable;

/**
 * do not process onComplete
 */
public class Ch2_9 {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma");
        source.map(String::length)
                .filter(i -> i>=5)
                .subscribe(i -> System.out.println("RECEIVE: " + i),
                        Throwable::printStackTrace);
    }
}
