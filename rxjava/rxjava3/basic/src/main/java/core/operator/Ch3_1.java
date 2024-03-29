package core.operator;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_1 {
    public static void main(String[] args) {
        Observable.range(1, 100)
                .takeWhile(i -> i<5)
                .subscribe(i -> System.out.println("RECEIVE: " + i));
    }
}
