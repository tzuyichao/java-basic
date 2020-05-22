package core.observable;

import io.reactivex.rxjava3.core.Observable;

public class ZipObservableLab {
    public static void main(String[] args) {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma");
        Observable<Integer> source2 = Observable.just(1, 2, 3);
        Observable<String> source = Observable.zip(source1, source2, (x, y) -> x + y);
        source.subscribe(s -> System.out.println("RECEIVE: " + s));
    }
}
