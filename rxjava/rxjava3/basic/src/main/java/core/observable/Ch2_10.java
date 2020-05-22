package core.observable;

import io.reactivex.rxjava3.core.Observable;

/**
 * 不理onError and onComplete
 */
public class Ch2_10 {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma");
        source.map(String::length)
                .filter(i -> i>=5)
                .subscribe(data -> System.out.println("RECEIVE: " + data));
    }
}
