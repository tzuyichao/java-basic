package rsb.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.ReplayProcessor;
import reactor.core.publisher.Sinks;
import reactor.test.StepVerifier;

public class ReplayProcessorTest {

    @Test
    public void replaceReplayProcessorWithSinksManyReplayTest() {
        int historySize = 2;
        Sinks.Many<String> stringMany = Sinks.many().replay().limit(historySize);
        produce(stringMany);
        consume(stringMany.asFlux());
    }

    private void produce(Sinks.Many<String> stringMany) {
        stringMany.tryEmitNext("1");
        stringMany.tryEmitNext("2");
        stringMany.tryEmitNext("3");
        stringMany.tryEmitComplete();
    }

    @Test
    public void replayProcessorTest() {
        int historySize = 2;
        boolean unbounded = false;
        ReplayProcessor<String> processor = ReplayProcessor.create(historySize, unbounded);

        produce(processor.sink());
        consume(processor);
    }

    private void produce(FluxSink<String> sink) {
        sink.next("1");
        sink.next("2");
        sink.next("3");
        sink.complete();
    }

    private void consume(Flux<String> publisher) {
        for(int i=0; i<5; i++) {
            StepVerifier.create(publisher)
                    .expectNext("2")
                    .expectNext("3")
                    .verifyComplete();
        }
    }
}
