package core.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

import static utils.ConcurrentUtil.sleep;

public class SubscribeOnLab {
    public static void main(String[] args) {
        Observable<String> source = Observable.create(emitter -> {
            System.out.println(Thread.currentThread().getName() + " invoked");
            emitter.onNext("Alpha");
            emitter.onNext("Beta");
            emitter.onNext("Gamma");
            emitter.onComplete();
        });
        Disposable disposable = source.subscribeOn(Schedulers.io())
                .subscribe(s -> System.out.println(Thread.currentThread().getName() + " RECEIVE :" + s));
        while(!disposable.isDisposed()) {
            sleep(300);
        }
        disposable = source.observeOn(Schedulers.io())
                .subscribe(s -> System.out.println(Thread.currentThread().getName() + " RECEIVE :" + s));
        while(!disposable.isDisposed()) {
            sleep(300);
        }
    }
}
