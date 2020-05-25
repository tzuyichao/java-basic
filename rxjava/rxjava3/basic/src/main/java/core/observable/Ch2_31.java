package core.observable;

import io.reactivex.rxjava3.core.Observable;

public class Ch2_31 {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma");
        source.firstElement()
                .subscribe(s -> System.out.println("Maybe RECEIVE: " + s),
                        e -> System.out.println("Maybe Error: " + e),
                        () -> System.out.println("Maybe Done."));
        source.first("Nil")
                .subscribe(s -> System.out.println("Single RECEIVE: " + s),
                        e -> System.out.println("Single Error: " + e));
    }
}
