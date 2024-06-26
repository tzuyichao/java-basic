package core.operator;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_3 {
    public static void main(String[] args) {
        Observable<String> items = Observable.just("Alpha", "Beta");
        items.filter(s -> s.startsWith("Z"))
                .defaultIfEmpty("None")
                .subscribe(s -> System.out.println("RECEIVE: " + s));
    }
}
