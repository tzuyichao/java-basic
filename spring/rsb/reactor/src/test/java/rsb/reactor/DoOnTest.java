package rsb.reactor;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

import java.util.ArrayList;

@Slf4j
public class DoOnTest {
    @Test
    public void doOn() {
        var signals = new ArrayList<Integer>();
        var nextValues = new ArrayList<Integer>();

        Flux<Integer> on = Flux.<Integer>create(integerFluxSink -> {

        })
                .doOnNext(nextValues::add);
    }
}
