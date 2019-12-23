package eventbus;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Sender extends AbstractVerticle {
    public static final String NEW_CHANNEL = "news-feed";

    public static void main(String[] args) {
        VertxOptions options = new VertxOptions();
        Vertx.clusteredVertx(options, res -> {
            if (res.succeeded()) {
                Vertx vertx = res.result();
                EventBus eventBus = vertx.eventBus();
                log.info("We now have a clustered event bus: " + eventBus);
                vertx.deployVerticle(new Sender());
            } else {
                log.info("Failed: " + res.cause());
            }
        });
    }

    @Override
    public void start() {
        EventBus eventBus = vertx.eventBus();

        vertx.setPeriodic(5000, v -> {
            eventBus.publish(NEW_CHANNEL, "some news!");
            log.info("publish some news!");
        });
    }
}
