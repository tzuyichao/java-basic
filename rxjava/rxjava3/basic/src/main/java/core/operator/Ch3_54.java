package core.operator;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_54 {
    public static void main(String[] args) {
        Observable.just("One", "Two", "Three")
                .doOnEach(s -> {
                    System.out.println(s.isOnNext() + ":" + s.isOnError() + ":" + s.isOnComplete());
                    System.out.println("doOnEach: " + s); })
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
