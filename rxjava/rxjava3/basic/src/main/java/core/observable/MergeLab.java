package core.observable;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class MergeLab {
    public static void main(String[] args) {
        Observable<String> source1 = Observable.create(emitter -> {
            new Thread(() -> {
                emitter.onNext("one");
                emitter.onNext("two");
                emitter.onComplete();
            }).start();
        });

        Observable<String> source2 = Observable.create(emitter -> {
            new Thread(() -> {
                emitter.onNext("three");
                emitter.onNext("four");
                emitter.onComplete();
            }).start();
        });
        Observable<String> mergedSource = Observable.merge(source1, source2);

        mergedSource.subscribe(new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                System.out.println("RECEIVED: " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.err.println(e.getMessage());
            }

            @Override
            public void onComplete() {
                System.out.println("Done.");
            }
        });
    }
}
