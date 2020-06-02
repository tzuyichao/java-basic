package core.reactiveoperator;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

import static utils.ConcurrentUtil.sleep;

public class Ch4_15 {
    public static void main(String[] args) {
        Observable<Long> source1 = Observable.interval(300, TimeUnit.MILLISECONDS);
        Observable<Long> source2 = Observable.interval(1, TimeUnit.SECONDS);
        Observable.combineLatest(source1, source2, (l1, l2) -> "Source1: " + l1 + ",Source2: " + l2)
                .subscribe(s -> System.out.println(System.currentTimeMillis() + " - RECEIVED: " + s));
        sleep(4000);
    }
}
