package rsb.reactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class TransformTest {
    @Test
    public void transform() {
        var finished = new AtomicBoolean();
        var letters = Flux
                .just("A", "B", "C")
                .log()
                .transform(stringFlux -> {
                    log.info("transform: {}", stringFlux);
                    return stringFlux.doFinally(signal -> finished.set(true));
                });
        StepVerifier.create(letters)
                .expectNextCount(3)
                .verifyComplete();
        assertThat(finished.get())
                .isTrue();
    }
}
