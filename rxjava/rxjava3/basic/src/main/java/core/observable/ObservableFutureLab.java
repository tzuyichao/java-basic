package core.observable;

import io.reactivex.rxjava3.core.Observable;
import lombok.extern.java.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static utils.ConcurrentUtil.sleep;

@Log
public class ObservableFutureLab {
    public static void main(String[] args) {
        Callable<String> task = () -> {
            log.info(Thread.currentThread().getName() + " called");
            sleep(300);
            return "Test";
        };
        ExecutorService executor = Executors.newFixedThreadPool(2);
        Future<String> future = executor.submit(task);
        Observable<String> source = Observable.fromFuture(future);

        source.map(String::length)
                .subscribe(l -> System.out.println("Observer 1:" + l));
        source.subscribe(s -> System.out.println("Observer 2:" + s));
        executor.shutdown();
    }
}
