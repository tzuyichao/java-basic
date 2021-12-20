package rsb.reactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Signal;
import reactor.core.publisher.SignalType;
import reactor.test.StepVerifier;

import java.util.ArrayList;

@Slf4j
public class DoOnTest {
    @Test
    public void doOn() {
        var signals = new ArrayList<Signal>();
        var nextValues = new ArrayList<Integer>();
        var subscriptions = new ArrayList<Subscription>();
        var exceptions = new ArrayList<Throwable>();
        var finallySignals = new ArrayList<SignalType>();

        Flux<Integer> on = Flux.<Integer>create(integerFluxSink -> {
                integerFluxSink.next(1);
                integerFluxSink.next(2);
                integerFluxSink.next(3);
                integerFluxSink.error(new IllegalArgumentException("oops"));
                integerFluxSink.complete();
            })
                .doOnNext(nextValues::add)
                .doOnEach(signals::add)
                .doOnSubscribe(subscriptions::add)
                .doOnError(IllegalArgumentException.class, exceptions::add)
                .doFinally(finallySignals::add);

        StepVerifier.create(on)
                .expectNext(1, 2, 3)
                .expectError(IllegalArgumentException.class)
                .verify();

        signals.forEach(s -> log.info("Signal: {}", s));
    }
}
