package core.observable;

import io.reactivex.rxjava3.core.Observable;

public class Ch2_20 {
    public static void main(String[] args) {
        Observable<String> empty = Observable.empty();
        // Observable.empty() only emitting the onComplete event
        empty.subscribe(s -> System.out.println("RECEIVE: " + s),
                Throwable::printStackTrace,
                () -> System.out.println("Done."));
    }
}
