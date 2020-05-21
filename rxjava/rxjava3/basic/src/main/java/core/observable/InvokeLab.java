package core.observable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import lombok.extern.java.Log;

@Log
public class InvokeLab {
    public static void getDataFromServiceWithCallback(String arg, Consumer<String> callback) {
        log.info("invoke remote service get data with argument: " + arg);
        String result = "[" + arg + "]";
        try {
            callback.accept(result);
        } catch (Throwable throwable) {
            log.info(throwable.getMessage());
        }
    }

    public static void main(String[] args) {
        Observable<String> source = Observable.create(emitter -> {
            getDataFromServiceWithCallback("data", data -> {
                emitter.onNext(data);
                emitter.onComplete();
            });
        });
        log.info("register subscriber");
        // getDataFromServiceWithCallback() 會被喚起兩次
        source.subscribe(s -> System.out.println("subscriber 1: " + s));
        log.info("Register subscriber 1 complete.");
        source.subscribe(s -> System.out.println("subscriber 2: " + s));
        log.info("Completed.");
    }
}
