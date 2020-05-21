package core.observable;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;
import utils.ConcurrentUtil;

public class Ch2_1 {
    public static void main(String[] args) {
        Observable<String> source = Observable.create(emitter -> {
            emitter.onNext("Alpha");
            emitter.onNext("Beta");
            emitter.onNext("Gamma");
            emitter.onComplete();
        });

        Observable<String> source2 = Observable.create(emitter -> {
            new Thread(() -> {
                emitter.onNext("Alpha");
                ConcurrentUtil.sleep(200);
                emitter.onNext("Beta");
                ConcurrentUtil.sleep(200);
                emitter.onNext("Gamma");
                ConcurrentUtil.sleep(200);
                // emitter.onComplete();
            }).start();
        });

        source.subscribe(s -> {
            System.out.println("RECEIVED: " + s);
        });

        source.subscribe(new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                System.out.println("DisposableObserver: " + s);
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

        source2.subscribe(new DisposableObserver<String>() {
            @Override
            public void onNext(@NonNull String s) {
                System.out.println(System.currentTimeMillis() + " RECEIVED: " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.err.println(e.getMessage());
            }

            // 如果emitter的onComplete()沒有呼叫，這個也不會被叫到
            @Override
            public void onComplete() {
                System.out.println(System.currentTimeMillis() +" Done.");
            }
        });
    }
}
