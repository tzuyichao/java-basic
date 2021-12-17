package rsb.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Sinks;
import reactor.test.StepVerifier;

public class EmitterProcessorTest {
    @Test
    public void emitterProcessorBySinks() {
        // 因為此時EmitterProcessor標示@Deprecated，JavaDoc建議改用這個。
        Sinks.Many<String> manyString = Sinks.many().multicast().onBackpressureBuffer();
        produce(manyString);
        consume(manyString.asFlux());
    }

    private void produce(Sinks.Many<String> sink) {
        sink.emitNext("1", (type, result) -> { return false; } );
        sink.tryEmitNext("2");
        sink.emitNext("3", (type, result) -> { return false; } );
        sink.emitComplete((type, result) -> { return false; });
    }

    @Test
    public void emitterProcessor() {
        EmitterProcessor<String> emitterProcessor = EmitterProcessor.create();
        produce(emitterProcessor.sink());
        consume(emitterProcessor);
    }

    private void produce(FluxSink<String> sink) {
        sink.next("1");
        sink.next("2");
        sink.next("3");
        sink.complete();
    }

    private void consume(Flux<String> publisher) {
        StepVerifier
                .create(publisher)
                .expectNext("1")
                .expectNext("2")
                .expectNext("3")
                .verifyComplete();
    }
}
