package core.operator;

import io.reactivex.rxjava3.core.Observable;

import java.util.Comparator;

public class Ch3_17 {
    public static void main(String[] args) {
        Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
                .sorted()
                .subscribe(System.out::print);
        System.out.println();
        Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
                .sorted(Comparator.reverseOrder())
                .subscribe(System.out::print);
        System.out.println();
        Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3)
                .sorted((c1, c2) -> (c1 % 2) - (c2 %2))
                .subscribe(System.out::print);
    }
}
