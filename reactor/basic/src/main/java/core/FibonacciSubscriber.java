package core;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class FibonacciSubscriber implements Subscriber<Long> {
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        this.subscription.request(10);
    }

    @Override
    public void onNext(Long aLong) {
        System.out.println("RECEIVED: " + aLong);
        this.subscription.cancel();
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        this.subscription = null;
    }

    @Override
    public void onComplete() {
        System.out.println("Done.");
        this.subscription = null;
    }
}
