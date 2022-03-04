package rsb.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Sinks;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.assertj.core.api.Assertions.assertThat;

public class HotStreamTest1 {
    @Test
    public void hot() {
        var first = new ArrayList<Integer>();
        var second = new ArrayList<Integer>();

        Sinks.Many<Integer> sink = Sinks.many().multicast().onBackpressureBuffer();
        sink.asFlux().subscribe(collect(first));
        sink.emitNext(1,  (type, result) -> { return false; });
        sink.emitNext(2,  (type, result) -> { return false; });
        sink.asFlux().subscribe(collect(second));
        sink.emitNext(3,  (type, result) -> { return false; });
        sink.emitComplete((type, result) -> {return false;});
        assertThat(first.size() > second.size());
    }

    Consumer<Integer> collect(List<Integer> collection) {
        return collection::add;
    }
}
