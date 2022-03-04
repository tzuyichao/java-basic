package rsb.reactor;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class HotStreamTest3 {
    private List<Integer> one = new ArrayList<>();
    private List<Integer> two = new ArrayList<>();
    private List<Integer> three = new ArrayList<>();

    private Consumer<Integer> subscribe(List<Integer> list) {
        return list::add;
    }

    @Test
    public void publish() {
        Flux<Integer> pileOn = Flux.just(1, 2, 3)
                .publish()
                .autoConnect(3)
                .subscribeOn(Schedulers.immediate());
        pileOn.subscribe(subscribe(one));
        assertThat(one.size(), is(equalTo(0)));
        pileOn.subscribe(subscribe(two));
        assertThat(two.size(), is(equalTo(0)));
        pileOn.subscribe(subscribe(three));
        assertThat(one.size(), is(equalTo(3)));
        assertThat(two.size(), is(equalTo(3)));
        assertThat(three.size(), is(equalTo(3)));
    }
}
