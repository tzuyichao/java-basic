package core.operator;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_4 {
    public static void main(String[] args) {
        Observable.just("Alpha", "Beta", "Gamma")
                .filter(s -> s.startsWith("Z"))
                .switchIfEmpty(Observable.just("Zeta", "Eta", "Theta"))
                .subscribe(s -> System.out.println("RECEIVE: " + s),
                        e -> System.err.println("RECEIVED ERROR: " + e));
    }
}
