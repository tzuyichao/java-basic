package core.observable;

import io.reactivex.rxjava3.core.Observable;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

import static utils.ConcurrentUtil.sleep;

public class Ch2_17 {
    public static void main(String[] args) {
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(s -> System.out.println(LocalDateTime.now().getSecond() + " " + s + " Mississippi"));
        sleep(3000);
    }
}
