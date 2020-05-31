package core.reactiveoperator;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

import static utils.ConcurrentUtil.sleep;

public class Ch4_4 {
    public static void main(String[] args) {
        Observable<String> source1 = Observable.interval(1, TimeUnit.SECONDS)
                .map(l -> l+1)
                .map(l -> "Source1: " + l + " seconds");
        Observable<String> source2 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> (l + 1) * 300)
                .map(l -> "Source2: " + l + " milliseconds");
        Observable.merge(source1, source2)
                .subscribe(System.out::println);
        sleep(3000);
    }
}
