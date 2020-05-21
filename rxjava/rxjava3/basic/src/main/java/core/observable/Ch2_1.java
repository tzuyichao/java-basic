package core.observable;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.DisposableObserver;

public class Ch2_1 {
    public static void main(String[] args) {
        Observable<String> source = Observable.create(emitter -> {
            emitter.onNext("Alpha");
            emitter.onNext("Beta");
            emitter.onNext("Gamma");
            emitter.onComplete();
        });

        source.subscribe(s -> {
            System.out.println(s);
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
    }
}
