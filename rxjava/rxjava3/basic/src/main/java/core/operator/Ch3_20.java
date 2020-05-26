package core.operator;

import io.reactivex.rxjava3.core.Observable;

public class Ch3_20 {
    public static void main(String[] args) {
        Observable<Integer> source = Observable.just(6, 2, 5, 7, 1, 4, 9, 8, 3);
        source.take(3)
                .subscribe(System.out::println);
        source.groupBy((k) -> {
            System.out.println("key selector: " + k);
            return k;
        }, (v) -> {
            System.out.println("value selector: " + v);
            return v;
        }, false, 3)
        .subscribe(s -> {
            System.out.println(s.getKey() + ":" + s.subscribe(i -> System.out.println(i.intValue())));
        });
        int window = 3;
        source.window(window, 1)
                .flatMapSingle(obs -> obs.reduce(0, (total, next) ->
                        total+next))
                .skipLast(window-1)
                .map(l -> l/3.0)
                .subscribe(System.out::println);
    }
}
