package core.reactiveoperator;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

import static utils.ConcurrentUtil.sleep;

public class Ch4_13 {
    public static void main(String[] args) {
        Observable<String> source1 = Observable.just("Alpha", "Beta", "Gamma");
        Observable<Integer> source2 = Observable.range(1, 6);
        Observable.zip(source1, source2, (s, i) -> s + "_" + i)
                .subscribe(s -> System.out.println("RECEIVED: " + s));

        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);
        Observable.zip(source1, seconds, (s, l) -> s)
                .subscribe(s -> System.out.println(System.currentTimeMillis() + " RECEIVED: " + s));
        sleep(4000);
    }
}
