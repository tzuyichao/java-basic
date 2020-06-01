package core;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class FirstExample {
    public static void main(String[] args) throws InterruptedException {
        Flux<String> flux = Flux.just("Hello, World!");
        flux.map(s -> "foo: " + s);
        flux.subscribe(s -> System.out.println("RECEIVED: " + s));

        Flux.interval(Duration.ofMillis(100))
                .filter(l -> l < 10)
                .subscribe(l -> System.out.println("RECEIVED: " + l));

        TimeUnit.SECONDS.sleep(3);
    }
}
