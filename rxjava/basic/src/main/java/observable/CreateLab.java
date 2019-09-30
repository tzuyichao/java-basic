package observable;

import io.reactivex.Observable;

public class CreateLab {
    public static void main(String[] args) {
        Observable<Object> observable = Observable.create(observer -> {
            observer.onNext("處理的數字是" + Math.random() * 100);
            observer.onComplete();
        });

        observable.subscribe(consumer -> {
            System.out.println("我處理的元素是：" + consumer);
        });

        observable.subscribe(consumer -> {
            System.out.println("我處理的元素是：" + consumer);
        });
    }
}
