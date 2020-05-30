package core.operator;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import static utils.ConcurrentUtil.sleep;

public class Ch3_57 {
    public static void main(String[] args) {
        Disposable disposable = Observable.just("Alpha", "Beta", "Gamma")
                .doOnSubscribe(d -> System.out.println("Subscribing!"))
                .doOnDispose(() -> System.out.println("Disposing!"))
                .subscribe(i -> System.out.println("RECEIVED: " + i));
        disposable.dispose();
        sleep(3000);
    }
}
