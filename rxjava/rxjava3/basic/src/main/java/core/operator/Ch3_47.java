package core.operator;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_47 {
    public static void main(String[] args) {
        Observable.just(5, 2, 4, 0, 3)
                .map(i -> 10 / i)
                .onErrorResumeNext(throwable -> Observable.just(-1).repeat(3))
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
