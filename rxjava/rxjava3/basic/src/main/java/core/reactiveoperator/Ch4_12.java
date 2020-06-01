package core.reactiveoperator;

import io.reactivex.rxjava3.core.Observable;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static utils.ConcurrentUtil.sleep;

public class Ch4_12 {
    public static void main(String[] args) {
        Observable<String> source1 = Observable.interval(1, TimeUnit.SECONDS)
                .take(2)
                .map(l -> l+1)
                .map(l -> "Source1: " + l);
        Observable<String> source2 = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> (l+1)*300)
                .map(l -> "Source2: " + l);
        Observable.amb(Arrays.asList(source1, source2))
                .subscribe(s -> System.out.println("RECEIVED: " + s));
        sleep(5000);
    }
}
