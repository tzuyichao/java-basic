package eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;

import static eventbus.Sender.NEW_CHANNEL;

@Slf4j
public class Receiver extends AbstractVerticle {
    public static void main(String[] args) {
        VertxOptions options = new VertxOptions();
        Vertx.clusteredVertx(options, res -> {
            if (res.succeeded()) {
                Vertx vertx = res.result();
                EventBus eventBus = vertx.eventBus();
                log.info("We now have a clustered event bus: " + eventBus);
                vertx.deployVerticle(new Receiver());
            } else {
                log.info("Failed: " + res.cause());
            }
        });
    }

    @Override
    public void start() {
        EventBus eventBus = vertx.eventBus();

        eventBus.consumer(NEW_CHANNEL, message -> log.info("receive news on consumer 1: {}", message.body()));

        log.info("Ready");
    }
}
