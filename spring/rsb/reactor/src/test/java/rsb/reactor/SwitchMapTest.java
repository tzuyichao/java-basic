package rsb.reactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

@Slf4j
public class SwitchMapTest {
    @Test
    public void switchMapWithLookaheads() {
        Flux<String> source = Flux.just("re", "rea", "react", "reactive")
                .delayElements(Duration.ofMillis(200))
                .log()
                .switchMap(this::lookup);
        StepVerifier.create(source)
                .expectNext("reactive -> reactive")
                .verifyComplete();
    }

    private Flux<String> lookup(String word) {
        log.info("Called: {}", word);
        return Flux.just(word + " -> reactive")
                .delayElements(Duration.ofMillis(500));
    }
}
