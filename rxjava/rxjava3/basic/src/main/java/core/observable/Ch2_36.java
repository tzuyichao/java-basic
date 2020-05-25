package core.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;

import static utils.ConcurrentUtil.sleep;

public class Ch2_36 {
    public static void main(String[] args) {
        Observable<Integer> source = Observable.create(observableEmitter -> {
            try {
                for(int i=0; i<1000; i++) {
                    if(!observableEmitter.isDisposed()) {
                        sleep(100);
                        observableEmitter.onNext(i);
                    }
                    if(observableEmitter.isDisposed()) {
                        return;
                    }
                }
                observableEmitter.onComplete();
            } catch (Throwable throwable) {
                observableEmitter.onError(throwable);
            }
        });

        Disposable disposable = source.subscribe(s -> System.out.println("RECEIVE: " + s));
        sleep(500);
        System.out.println("Call dispose()...");
        disposable.dispose();
        sleep(500);
    }
}
