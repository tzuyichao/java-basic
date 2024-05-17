package core;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import reactor.util.function.Tuples;

public class StepVerifierTests {
    @Test
    public void testExpectation() {
        Flux<Long> fibonacciGenerator = Flux.generate(() -> Tuples.<Long, Long>of(0L, 1L), (state, sink) -> {
            if(state.getT1() < 0) {
                sink.complete();
            } else {
                sink.next(state.getT1());
            }
            System.out.println("generating next of " + state.getT1());
            return Tuples.of(state.getT2(), state.getT1() + state.getT2());
        });
        StepVerifier.create(fibonacciGenerator.take(10))
                .expectNext(0L, 1L, 1L)
                .expectNextCount(7)
                .expectComplete()
                .verify();
    }

    @Test
    public void testErrorExpectation() {
        Flux<Long> fibonacciGenerator = Flux.generate(() -> Tuples.<Long, Long>of(0L, 1L), (state, sink) -> {
            if(state.getT1() > 30) {
                sink.error(new IllegalStateException("Value out of bound"));
            }
            if(state.getT1() < 0) {
                sink.complete();
            } else {
                sink.next(state.getT1());
            }
            System.out.println("generating next of " + state.getT1());
            return Tuples.of(state.getT2(), state.getT1() + state.getT2());
        });
        StepVerifier.create(fibonacciGenerator.take(10))
                .expectNextCount(9)
                .expectErrorSatisfies(x -> {
                    assert(x instanceof IllegalStateException);
                })
                .verify();
    }
}
