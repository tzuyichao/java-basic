package observable;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;

import java.util.concurrent.TimeUnit;

public class FlowableLab {
    public static void main(String[] args) throws InterruptedException {
        Disposable disposable = Flowable.just("Hello world")
//                .delay(1, TimeUnit.SECONDS)
                .subscribe(msg -> {
                    System.out.println(Thread.currentThread().getName() + ": " + msg);
                });
        TimeUnit.SECONDS.sleep(5);
        disposable.dispose();

        Disposable disposable2 = Flowable.just("Hello world")
                .delay(1, TimeUnit.SECONDS)
                .subscribeWith(new DisposableSubscriber<String>() {

                    @Override
                    public void onNext(String s) {
                        System.out.println(Thread.currentThread().getName() + ":" + s);
                    }

                    @Override
                    public void onError(Throwable t) {
                        t.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println(Thread.currentThread().getName() + " Completed");
                    }
                });
        TimeUnit.SECONDS.sleep(5);
        disposable2.dispose();
    }
}
