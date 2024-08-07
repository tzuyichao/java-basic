package core.operator;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_52 {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma")
                .doOnComplete(() -> System.out.println("Source is done emitting!"))
                .map(String::length)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
