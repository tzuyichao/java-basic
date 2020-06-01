package core;

import reactor.core.publisher.Mono;

import java.time.Duration;

public class MonoExample {
    public static void main(String[] args) {
        Mono.just(1)
                .map(i -> "foo: " + i)
                .or(Mono.delay(Duration.ofMillis(100)).map(i -> "bar: " + i))
                .subscribe(s -> System.out.println(System.currentTimeMillis() + ":" + s),
                        Throwable::printStackTrace,
                        () -> System.out.println("Completed."));
    }
}
