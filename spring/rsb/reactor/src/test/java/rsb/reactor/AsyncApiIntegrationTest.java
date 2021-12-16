package rsb.reactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.test.StepVerifier;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class AsyncApiIntegrationTest {
    private final ExecutorService executorService = Executors.newFixedThreadPool(1);

    @Test
    public void async() {
        Flux<Integer> integers = Flux.create(emitter -> this.launch(emitter, 5));
        log.info("Running Test from {}", Thread.currentThread().getName());
        StepVerifier.create(integers.doFinally(signalType -> this.executorService.shutdown()))
                .expectNextCount(5)
                .verifyComplete();
    }

    private void launch(FluxSink<Integer> integerFluxSink, int count) {
        this.executorService.submit(() -> {
            var integer = new AtomicInteger();
            assertThat(integerFluxSink).isNotNull();
            while(integer.get() < count) {
                log.info("Generate number from {}", Thread.currentThread().getName());
                double random = Math.random();
                integerFluxSink.next(integer.incrementAndGet());
                this.sleep((long)(random * 1_000));
            }
            integerFluxSink.complete();
        });
    }

    private void sleep(long s) {
        try {
            Thread.sleep(s);
        } catch(InterruptedException e) {
            log.error(e.getMessage());
        }
    }
}