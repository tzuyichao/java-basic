package core;

import reactor.core.publisher.Flux;

public class FirstExample {
    public static void main(String[] args) {
        Flux<String> flux = Flux.just("Hello, World!");
        flux.map(s -> "foo: " + s);
        flux.subscribe(s -> System.out.println("RECEIVED: " + s));
    }
}
