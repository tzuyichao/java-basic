package core.operator;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_46 {
    public static void main(String[] args) {
        Observable.just(5, 2, 4, 0, 3)
                .map(i -> 10/i)
                .onErrorResumeWith(Observable.empty())
                .subscribe(s -> System.out.println("RECEIVED: " + s),
                        e -> System.err.println("RECEIVED ERROR: " + e),
                        () -> System.out.println("Done."));
    }
}
