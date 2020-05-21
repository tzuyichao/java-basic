package core.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * Packt, Learning RxJava, 2ed
 */
public class Ch1_1 {
    public static void main(String[] args) throws InterruptedException {
        Observable<String> myStrings = Observable.just("Alpha", "Beta", "Gamma");
        myStrings
                .subscribe(s -> {
                    System.out.println(Thread.currentThread().getName() + ":" + s);
                });
        Disposable disposable = myStrings.subscribeOn(Schedulers.newThread())
                .subscribe(s -> {
                    System.out.println(Thread.currentThread().getName() + ":" + s);
                });
        while(!disposable.isDisposed()) {
            TimeUnit.MICROSECONDS.sleep(200);
        }
    }
}
