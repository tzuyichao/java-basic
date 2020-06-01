package core.reactiveoperator;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

import static utils.ConcurrentUtil.sleep;

public class Ch4_7 {
    public static void main(String[] args) {
        Observable.just(2, 3, 10, 7)
                .flatMap(i -> Observable.interval(i, TimeUnit.SECONDS).map(i2 -> i + "s interval: " + (i+1)*i + " second elapse"))
                .subscribe(s -> System.out.println("RECEIVED: " + s));
        sleep(12000);
    }
}
