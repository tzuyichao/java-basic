package core;

import org.reactivestreams.Publisher;
import org.reactivestreams.tck.PublisherVerification;
import org.reactivestreams.tck.TestEnvironment;

public class FibonacciPublisherVerifier extends PublisherVerification<Integer> {
    public FibonacciPublisherVerifier() {
        super(new TestEnvironment());
    }

    @Override
    public Publisher<Integer> createPublisher(long l) {
        return new FibonacciPublisher();
    }

    @Override
    public Publisher<Integer> createFailedPublisher() {
        return null;
    }
}
