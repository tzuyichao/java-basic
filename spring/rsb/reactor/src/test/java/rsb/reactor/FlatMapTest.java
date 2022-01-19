package rsb.reactor;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.Executors;

@Log
public class FlatMapTest {

    @Test
    public void flatMap() {
        Flux<Integer> data = Flux.just(new Pair(1, 300), new Pair(2, 200), new Pair(3, 100))
                .flatMap(it -> this.delayReplyFor(it.id, it.delay));
        StepVerifier.create(data)
                .expectNext(3, 2, 1)
                .verifyComplete();
    }

    private Flux<Integer> delayReplyFor(Integer i, long delay) {
        log.info(() -> Thread.currentThread().getName() + ": " + i + " " + delay);
        return Flux.just(i).delayElements(Duration.ofMillis(delay));
    }

    @Test
    public void testMap() {
        Flux<Integer> data = Flux
                .just(new Pair(1, 300), new Pair(2, 200), new Pair(3, 100))
                .flatMap(it -> this.delayReplyFor(it.id, it.delay))
                .publishOn(Schedulers.fromExecutor(Executors.newFixedThreadPool(3)))
                .log();
        StepVerifier.create(data)
                .expectNext(3, 2, 1)
                .verifyComplete();
    }

    @Test
    public void testMap2() {
        Flux<Integer> data = Flux
                .just(new Pair(1, 300), new Pair(2, 200), new Pair(3, 100))
                .flatMap(it -> this.delayReplyFor(it.id, it.delay))
                .log();
        StepVerifier.create(data)
                .expectNext(3, 2, 1)
                .verifyComplete();
    }

    @Test
    public void testMap3() {
        Flux<Integer> data = Flux
                .just(new Pair(1, 300), new Pair(2, 200), new Pair(3, 100))
                .flatMap(it -> this.delayReplyFor(it.id, it.delay))
                .publishOn(Schedulers.parallel())
                .log();
        StepVerifier.create(data)
                .expectNext(3, 2, 1)
                .verifyComplete();
    }

    @Test
    public void testMap4() {
        Flux<Integer> data = Flux
                .just(new Pair(1, 300), new Pair(2, 200), new Pair(3, 100))
                .flatMap(it -> this.delayReplyFor(it.id, it.delay))
                .publishOn(Schedulers.newBoundedElastic(10, 10, "elastic-bounded"))
                .log();
        StepVerifier.create(data)
                .expectNext(3, 2, 1)
                .verifyComplete();
    }

    private Mono<String> getMonoData() {
        return Mono.just("Test").delayElement(Duration.ofMillis(200));
    }

    private Flux<String> getFluxData(String str) {
        var idx = new Integer[] {1, 2, 3, 4};
        return Flux.fromArray(idx)
                .map(elem -> str + "-" + elem)
                .delayElements(Duration.ofMillis(100));
    }

    @Test
    public void testZipWhen() {
        var data = getMonoData();
        var result = data.zipWhen(d -> {
            return getFluxData(d).collectList();
        }).map(tuple -> {
           return tuple.getT1() + "-" + tuple.getT2();
        });
        //System.out.println(result.block());
        StepVerifier.create(result)
                .expectNext("Test-[Test-1, Test-2, Test-3, Test-4]")
                .verifyComplete();
    }

    @AllArgsConstructor
    static class Pair {
        private int id;
        private long delay;
    }
}
