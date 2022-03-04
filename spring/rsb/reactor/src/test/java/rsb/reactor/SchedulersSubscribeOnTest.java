package rsb.reactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@Slf4j
public class SchedulersSubscribeOnTest {
    @Test
    public void subscribeOn() {
        var rsbThreadName = SchedulersSubscribeOnTest.class.getName();
        var map = new ConcurrentHashMap<String, AtomicInteger>();
        var executor = Executors.newFixedThreadPool(5, runnable -> {
            Runnable wrapper = () -> {
                var key = Thread.currentThread().getName();
                log.info("in wrapper: {}", key);
                var result = map.computeIfAbsent(key, s -> new AtomicInteger());
                result.incrementAndGet();
                runnable.run();
            };
            return new Thread(wrapper, rsbThreadName);
        });

        Scheduler scheduler = Schedulers.fromExecutor(executor);
        Mono<Integer> integerMono = Mono.just(1)
                .subscribeOn(scheduler)
                .doFinally(signal -> {
                    map.forEach((k, v) -> log.info(k + "=" + v));
                });
        StepVerifier.create(integerMono).expectNextCount(1).verifyComplete();
        var atomicInteger = map.get(rsbThreadName);
        assertThat(atomicInteger.get(), is(equalTo(1)));
    }
}
