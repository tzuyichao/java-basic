package core.operator;

import io.reactivex.rxjava3.core.Observable;

import java.util.Arrays;
import java.util.List;

public class Ch3_16 {
    public static void main(String[] args) {
        Observable<String> menu = Observable.just("Coffee", "Tea", "Espresso", "Latte");
        System.out.println("startWithItem()");
        menu.startWithItem("COFFEE SHOP MENU")
                .subscribe(s -> System.out.println(s));

        System.out.println("startWithArray()");
        menu.startWithArray("COFFEE SHOP MENU", "------------------")
                .subscribe(s -> System.out.println(s));
        System.out.println("startWithIterable()");
        List<String> header = Arrays.asList("COFFEE SHOP MENU", "------------------");
        menu.startWithIterable(header)
                .subscribe(s -> System.out.println(s));
    }
}
