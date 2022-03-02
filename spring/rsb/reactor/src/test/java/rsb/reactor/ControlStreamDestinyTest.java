package rsb.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ControlStreamDestinyTest {
    @Test
    public void handle() {
        StepVerifier.create(this.handle(5, 4))
                .expectNext(0, 1, 2, 3)
                .expectError(IllegalArgumentException.class)
                .verify();
    }

    Flux<Integer> handle(int max, int numberToError) {
        return Flux.range(0, max)
                .handle((value, sink) -> {
                    var upTo = Stream.iterate(0, i -> i < numberToError, i -> i + 1)
                            .collect(Collectors.toList());
                    if(upTo.contains(value)) {
                        sink.next(value);
                        return;
                    }
                    if(value == numberToError) {
                        sink.error(new IllegalArgumentException("No " + numberToError + " for you"));
                        return;
                    }
                    sink.complete();
                });
    }
}
