package core.observable;

import io.reactivex.rxjava3.core.Observable;

public class Ch2_22 {
    public static void main(String[] args) {
        Observable.error(new Exception("Crash and burn!"))
                .subscribe(s -> System.out.println("RECEIVE: " + s),
                        (e) -> System.err.println("Error captured: " + e),
                        () -> System.out.println("Done."));
    }
}
