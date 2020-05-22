package core.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;

public class Ch2_8 {
    public static void main(String[] args) {
        Observable<String> source = Observable.just("Alpha", "Beta", "Gamma");
        Consumer<Integer> onNext = i -> System.out.println("RECEIVE: " + i);
        Consumer<Throwable> onError = Throwable::printStackTrace;
        Action onComplete = () -> System.out.println("Done.");

        source.map(String::length)
                .filter(i -> i>=5)
                .subscribe(onNext, onError, onComplete);

        source.map(String::length)
                .filter(i -> i>=5)
                .subscribe(i -> System.out.println("A RECEIVE: " + i),
                        Throwable::printStackTrace,
                        () -> System.out.println("A Done."));
    }
}
