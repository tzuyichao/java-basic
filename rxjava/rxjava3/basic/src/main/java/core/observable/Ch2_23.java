package core.observable;

import io.reactivex.rxjava3.core.Observable;

public class Ch2_23 {
    private static int start = 1;
    private static int count = 3;

    public static void main(String[] args) {
        Observable<Integer> source1 = Observable.range(start, count);
        Observable<Integer> source2 = Observable.defer(() -> Observable.range(start, count));
        source1.subscribe(s -> System.out.println("Observer 1-1:" + s));
        source2.subscribe(s -> System.out.println("Observer 2-1:" + s));
        count = 5;
        source1.subscribe(s -> System.out.println("Observer 1-2:" + s));
        source2.subscribe(s -> System.out.println("Observer 2-2:" + s));
    }
}
