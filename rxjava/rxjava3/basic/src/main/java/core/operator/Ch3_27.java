package core.operator;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_27 {
    public static void main(String[] args) {
        System.out.println("isEmpty() lab: contains 'z'?");
        Observable.just("One", "Two", "Three")
                .filter(s -> s.contains("z"))
                .isEmpty()
                .subscribe(s -> System.out.println("RECEIVED: " + s));
        Observable.just("One", "Twoz", "Three")
                .filter(s -> s.contains("z"))
                .isEmpty()
                .subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
