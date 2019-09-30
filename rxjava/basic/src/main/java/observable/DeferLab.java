package observable;

import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;

public class DeferLab {
    private static void log(Object message) {
        System.out.println(Thread.currentThread().getName() + ":" + message);
    }

    public static void main(String[] args) {
        String[] data = {"1", "2", "3", "4"};
        Observable.defer(() -> {
            return Observable.fromArray(data);
        }).subscribe(System.out::println, Throwable::printStackTrace, () -> {
            System.out.println("Completed");
        });

        Observable<String> observable = Observable.defer(() -> {
            return Observable.fromArray(data);
        }).delay(1, TimeUnit.SECONDS);
        System.out.println(observable);
        observable.subscribe(DeferLab::log, Throwable::printStackTrace, () -> {
            System.out.println("Completed");
        });
        System.out.println(observable);
    }
}
