package core.observable;

import io.reactivex.rxjava3.core.Maybe;

public class Ch2_30a {
    public static void main(String[] args) {
        Maybe<Integer> source = Maybe.just(100);
        //　不會收到onComplete event
        source.subscribe(s -> System.out.println("Process 1:" + s),
                Throwable::printStackTrace,
                () -> System.out.println("Process 1 done."));
        Maybe<Integer> empty = Maybe.empty();
        empty.subscribe(s -> System.out.println("Process 2:" + s),
                Throwable::printStackTrace,
                () -> System.out.println("Process 2 done."));
    }
}
