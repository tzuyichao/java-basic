package core.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

import static utils.ConcurrentUtil.sleep;

public class Ch2_19 {
    public static void main(String[] args) {
        ConnectableObservable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS).publish();
        seconds.subscribe(l -> System.out.println("Observer 1: " + l));
        seconds.connect();
        sleep(3000);
        seconds.subscribe(l -> System.out.println("Observer 2: " + l));
        sleep(3000);
    }
}
