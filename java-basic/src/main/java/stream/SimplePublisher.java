package stream;

import java.util.Iterator;
import java.util.concurrent.Flow;
import java.util.stream.IntStream;

/**
 * https://blog.softwaremill.com/how-not-to-use-reactive-streams-in-java-9-7a39ea9c2cb3
 */
public class SimplePublisher implements Flow.Publisher<Integer> {
    private final Iterator<Integer> iterator;

    public SimplePublisher(int count) {
        this.iterator = IntStream.rangeClosed(1, count).iterator();
    }

    @Override
    public void subscribe(Flow.Subscriber<? super Integer> subscriber) {
        iterator.forEachRemaining(subscriber::onNext);
        subscriber.onComplete();
    }

    public static void main(String[] args) {
        new SimplePublisher(10).subscribe(new Flow.Subscriber<>() {

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                System.out.println(subscription.toString());
            }

            @Override
            public void onNext(Integer item) {
                System.out.println("item = [" + item + "]");
            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("completed.");
            }
        });
    }
}
