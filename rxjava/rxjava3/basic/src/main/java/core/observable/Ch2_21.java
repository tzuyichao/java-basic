package core.observable;

import io.reactivex.rxjava3.core.Observable;

import static utils.ConcurrentUtil.sleep;

public class Ch2_21 {
    public static void main(String[] args) {
        Observable<String> empty = Observable.never();
        empty.subscribe(s -> System.out.println("RECEIVE: " + s),
                Throwable::printStackTrace,
                () -> System.out.println("Done."));
        sleep(3000);
        System.out.println("Nothing happened.");
    }
}
