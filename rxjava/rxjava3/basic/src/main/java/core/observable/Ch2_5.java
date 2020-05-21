package core.observable;

import io.reactivex.rxjava3.core.Observable;

public class Ch2_5 {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Alpha", "Beta", "●●●●", "Gamma");
        source.map(s -> s.codePointCount(0, s.length()))
                .filter(i -> i >= 5)
                .subscribe(s -> System.out.println("RECEIVE: " + s));
        source.map(s -> s.length())
                .filter(i -> i >= 5)
                .subscribe(s -> System.out.println("RECEIVE: " + s));
    }
}
