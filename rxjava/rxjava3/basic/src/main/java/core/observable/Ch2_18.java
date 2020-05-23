package core.observable;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

import static utils.ConcurrentUtil.sleep;

public class Ch2_18 {
    public static void main(String[] args) {
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);
        seconds.subscribe(s -> System.out.println("Observer 1: " + s));
        sleep(3000);
        seconds.subscribe(s -> System.out.println("Observer 2: " + s));
        sleep(3000);
    }
}
