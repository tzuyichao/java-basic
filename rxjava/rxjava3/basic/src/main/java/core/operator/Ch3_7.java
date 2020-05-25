package core.operator;

import io.reactivex.rxjava3.core.Observable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static utils.ConcurrentUtil.sleep;

public class Ch3_7 {
    public static void main(String[] args) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("ss:SSS");
        System.out.println(LocalDateTime.now().format(f));
        Observable.interval(300, TimeUnit.MILLISECONDS)
                .take(3, TimeUnit.SECONDS)
                .subscribe(s -> System.out.println(LocalDateTime.now().format(f) + " RECEIVED: " + s));
        sleep(5000);
        Observable.interval(1, TimeUnit.SECONDS)
                .skip(3, TimeUnit.SECONDS)
                .subscribe(s -> System.out.println(LocalDateTime.now().format(f) + " RECEIVED: " + s));
        sleep(5000);
    }
}
