package core.observable;

import io.reactivex.rxjava3.core.Observable;
import utils.ConcurrentUtil;

import java.util.concurrent.TimeUnit;

public class Ch1_3 {
    public static void main(String[] args) {
        Observable<Long> secondIntervals = Observable.interval(1, TimeUnit.SECONDS);
        secondIntervals.subscribe(s -> {
            System.out.println(String.format("%s: %s", System.currentTimeMillis(), s));
        });
        ConcurrentUtil.sleep(5000);
    }
}
