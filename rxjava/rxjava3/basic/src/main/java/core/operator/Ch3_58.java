package core.operator;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;

import static utils.ConcurrentUtil.sleep;

public class Ch3_58 {
    public static void main(String[] args) {
        Disposable disposable = Observable.interval(1, TimeUnit.SECONDS)
                .doOnSubscribe(disposable1 -> System.out.println("Subscribing! "))
                .doOnDispose(() -> System.out.println("Disposing!"))
                .subscribe(s -> System.out.println("RECEIVED: " + s));
        sleep(3000);
        disposable.dispose();
        sleep(3000);
    }
}
