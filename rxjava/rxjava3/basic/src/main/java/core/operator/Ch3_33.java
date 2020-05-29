package core.operator;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_33 {
    public static void main(String[] args) {
        Observable.just("Beta", "Alpha", "Gamma")
                .toSortedList()
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
