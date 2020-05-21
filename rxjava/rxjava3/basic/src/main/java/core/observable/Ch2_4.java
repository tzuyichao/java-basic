package core.observable;

import io.reactivex.rxjava3.core.Observable;

public class Ch2_4 {
    public static void main(String[] args) {
        Observable<String> source = Observable.create(emitter -> {
            try {
                emitter.onNext("Alpha");
                emitter.onNext("Beta");
                emitter.onNext("Gamma");
                emitter.onComplete();
            } catch(Throwable throwable) {
                emitter.onError(throwable);
            }
        });

        source.map(String::length)
                .filter(l -> l >= 5)
                .subscribe(l -> System.out.println("RECEIVE: " + l));
    }
}
