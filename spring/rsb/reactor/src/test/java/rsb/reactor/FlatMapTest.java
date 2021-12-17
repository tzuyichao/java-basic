package rsb.reactor;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

@Slf4j
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
        log.info("{}: {}", i, delay);
        return Flux.just(i).delayElements(Duration.ofMillis(delay));
    }

    @AllArgsConstructor
    static class Pair {
        private int id;
        private long delay;
    }
}
