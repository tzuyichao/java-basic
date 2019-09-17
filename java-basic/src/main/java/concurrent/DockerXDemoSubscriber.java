package concurrent;

import java.util.concurrent.Flow;
import java.util.concurrent.TimeUnit;

/**
 * Sample code from Java編程方法論: RxJava
 *
 * @param <T>
 */
public class DockerXDemoSubscriber<T> implements Flow.Subscriber<T> {
    private String name;
    private Flow.Subscription subscription;
    final long bufferSize;
    long count;

    public String getName() {
        return this.name;
    }

    public Flow.Subscription getSubscription() {
        return this.subscription;
    }

    public DockerXDemoSubscriber(long bufferSize, String name) {
        this.bufferSize = bufferSize;
        this.name = name;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        (this.subscription = subscription).request(bufferSize);
        System.out.println("start onSubscribe()");
        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNext(T item) {
        System.out.println(" #### " + Thread.currentThread().getName() + " name: " + name + " item: " + item + " #####");
        System.out.println(name + " received: " + item);
        try {
            TimeUnit.MILLISECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
    }

    @Override
    public void onComplete() {
        System.out.println("Completed");
    }
}
