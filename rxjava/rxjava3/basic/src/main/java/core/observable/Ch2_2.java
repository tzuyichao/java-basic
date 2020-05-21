package core.observable;

import io.reactivex.rxjava3.core.Observable;

import java.util.Random;

public class Ch2_2 {
    public static void main(String[] args) {
        Observable<String> source = Observable.create(emitter -> {
            try {
                emitter.onNext("Alpha");
                emitter.onNext("Beta");
                Random random = new Random();
                random.setSeed(System.currentTimeMillis());
                int val = random.nextInt(100);
                if(val > 50) {
                    throw new IllegalStateException("Error.....");
                }
                emitter.onNext("Gamma");
                emitter.onComplete();
            } catch(Throwable e) {
                emitter.onError(e);
            }
        });

        source.subscribe(s -> System.out.println("RECEIVED: " + s),
                Throwable::printStackTrace,
                () -> System.out.println("Completed."));
    }
}
