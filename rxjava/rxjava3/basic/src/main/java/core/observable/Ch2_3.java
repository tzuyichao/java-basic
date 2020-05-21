package core.observable;

import io.reactivex.rxjava3.core.Observable;

public class Ch2_3 {
    public static void main(String[] args) {
        Observable<String> source = Observable.create(emitter -> {
            try {
                emitter.onNext("Alpha");
                emitter.onNext("Beta");
                emitter.onNext("Gamma");
                emitter.onComplete();
            } catch(Throwable e) {
                emitter.onError(e);
            }
        });

        Observable<Integer> lengths = source.map(String::length);
        Observable<Integer> filterLengths = lengths.filter(i -> i>=5);
        filterLengths.subscribe(System.out::println);

        Observable<String> filterContent = source.filter(s -> s.length() >= 5);
        filterContent.subscribe(System.out::println);
    }
}
