package core.observable;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static utils.ConcurrentUtil.sleep;

public class Ch2_32 {
    public static void main(String[] args) {
        Completable.fromRunnable(() -> runProcess())
                .subscribe(() -> System.out.println(Thread.currentThread().getName() + " Done"));
        System.out.println("Others");
        Disposable disposable = Observable.just("aaa")
                .subscribeOn(Schedulers.io())
                .subscribe(s -> System.out.println(Thread.currentThread().getName() + " RECEIVE: " + s));
        while(!disposable.isDisposed()) {
            sleep(300);
        }
    }

    private static void runProcess() {
        System.out.println(Thread.currentThread().getName() + " running");
    }
}
