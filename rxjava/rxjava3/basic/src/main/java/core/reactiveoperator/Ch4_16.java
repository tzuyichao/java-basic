package core.reactiveoperator;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

import static utils.ConcurrentUtil.sleep;

public class Ch4_16 {
    public static void main(String[] args) {
        Observable<Long> source1 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .doOnNext(l -> System.out.println("Source1 next: " + l));
        Observable<Long> source2 = Observable.interval(1, TimeUnit.SECONDS)
                .doOnNext(l -> System.out.println("Source2 next: " + l));
        source2.withLatestFrom(source1, (l1, l2) -> "Source2: " + l1 + ", Source1: " + l2)
                .subscribe(s -> System.out.println("RECEIVED: " + s));
        sleep(5000);
    }
}
