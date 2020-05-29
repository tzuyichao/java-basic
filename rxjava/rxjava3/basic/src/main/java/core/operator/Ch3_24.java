package core.operator;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_24 {
    public static final String INITIAL_STR = "";
    public static final String SEP_STR = "-";

    public static void main(String[] args) {
        Observable.just(5, 3, 7)
                .reduce(INITIAL_STR, (total, i) -> total + (total.equals(INITIAL_STR)?INITIAL_STR:SEP_STR) + i)
                .subscribe(s -> System.out.println("RECEIVE: " + s));
    }
}
