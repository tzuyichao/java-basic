package core.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;

import static utils.ConcurrentUtil.sleep;

public class Ch2_33 {
    public static void main(String[] args) {
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);
        Disposable disposable = seconds.subscribe(l -> System.out.println("RECEIVED: " + l));
        sleep(5000);
        System.out.println("call dispose()");
        disposable.dispose();
        System.out.println("sleep 5 second for test");
        sleep(5000);
    }
}
