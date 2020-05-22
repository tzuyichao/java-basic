package core.observable;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class Ch2_7 {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma");
        Observer<Integer> myObserver = new Observer<>(){
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("onSubscribe: " + d.toString());
            }

            @Override
            public void onNext(@NonNull Integer value) {
                System.out.println("RECEIVE: " + value);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.err.println("Error...");
                e.printStackTrace();
            }

            @Override
            public void onComplete() {
                System.out.println("Done.");
            }
        };
        source.map(String::length)
                .filter(i -> i>=5)
                .subscribe(myObserver);
    }
}
