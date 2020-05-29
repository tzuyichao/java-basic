package core.operator;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_29 {
    public static void main(String[] args) {
        Observable<String> source1 = Observable.just("One", "Two", "Three");
        Observable<String> source2 = Observable.just("One", "Two", "Three");
        Observable<String> source3 = Observable.just("Two", "One", "Three");
        Observable<String> source4 = Observable.just("One", "Two");

        Observable.sequenceEqual(source1, source2)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
        Observable.sequenceEqual(source1, source3)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
        Observable.sequenceEqual(source1, source4)
                .subscribe(s -> System.out.println("RECEIVED: " + s));

    }
}
