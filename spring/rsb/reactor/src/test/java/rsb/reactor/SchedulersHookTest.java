package rsb.reactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

@Slf4j
public class SchedulersHookTest {
    @Test
    public void onScheduleHook() {
        var counter = new AtomicInteger();
        Schedulers.onScheduleHook("my hook", runnable -> ()-> {
            var threadName = Thread.currentThread().getName();
            counter.incrementAndGet();
            log.info("before execution: " + threadName);
            runnable.run();
            log.info("after execution: " + threadName);
        });
        Flux<Integer> integerFlux = Flux.just(1, 2, 3)
                .delayElements(Duration.ofMillis(1))
                .subscribeOn(Schedulers.immediate());
        StepVerifier.create(integerFlux)
                .expectNext(1, 2, 3)
                .verifyComplete();
        assertThat(counter.get(), is(equalTo(3)));
    }
}
