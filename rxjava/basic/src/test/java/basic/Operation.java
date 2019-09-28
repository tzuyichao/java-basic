package basic;

import io.reactivex.Observable;
import org.junit.Test;

import java.time.LocalDate;
import java.util.concurrent.TimeUnit;

public class Operation {
    @Test
    public void distinct_test() {
        Observable<LocalDate> dates = Observable.just(
                LocalDate.of(2018, 1, 3),
                LocalDate.of(2018, 3, 4),
                LocalDate.of(2018, 1, 5),
                LocalDate.of(2018, 11, 3)
        );
        dates.map(LocalDate::getMonth)
                .distinct()
                .subscribe(System.out::println);
        System.out.println("##########################");
        dates.distinct(LocalDate::getMonth)
                .subscribe(System.out::println);
    }

    @Test
    public void amb_test() {
        Integer[] numbers = {1, 2, 13, 34, 15, 17};
        String[] fruits = {"蘋果", "梨", "李子", "荔枝", "芒果"};
        Observable<Integer> source1 = Observable.fromArray(numbers).delay(1, TimeUnit.SECONDS);
        Observable<String> source2 = Observable.fromArray(fruits);

        Observable.ambArray(source1, source2)
                .forEach(System.out::println);
    }
}
