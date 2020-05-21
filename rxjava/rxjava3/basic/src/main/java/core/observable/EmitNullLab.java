package core.observable;

import io.reactivex.rxjava3.core.Observable;

public class EmitNullLab {
    public static void main(String[] args) {
        Observable<String> source = Observable.create(emitter -> {
            try {
                emitter.onNext("Alpha");
                emitter.onNext(null);
                emitter.onNext("Beta");
                emitter.onComplete();
            } catch(Throwable throwable) {
                emitter.onError(throwable);
            }
        });

        source.subscribe(
                s -> System.out.println("RECEIVE: " + s),
                throwable -> {
                    System.err.println("in onError:");
                    System.err.println("Error: " + throwable.getMessage());
                });
    }
}
