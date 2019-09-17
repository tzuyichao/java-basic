package concurrent;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Flow;

public class DockerXDemoPublisher<T> implements Flow.Publisher<T>, AutoCloseable {
    private final ExecutorService executorService;
    private CopyOnWriteArrayList<DockerXDemoSubscription> list = new CopyOnWriteArrayList<>();

    public DockerXDemoPublisher(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public void subscribe(Flow.Subscriber<? super T> subscriber) {

    }

    @Override
    public void close() throws Exception {
        list.forEach(e -> {

        });
    }

    static class DockerXDemoSubscription<T> implements Flow.Subscription {

        @Override
        public void request(long n) {

        }

        @Override
        public void cancel() {

        }
    }
}
