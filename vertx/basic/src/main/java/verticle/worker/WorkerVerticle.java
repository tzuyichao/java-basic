package verticle.worker;

import io.vertx.core.AbstractVerticle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WorkerVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        log.info("[Worker] Starting in {}", Thread.currentThread().getName());

        vertx.eventBus().<String>consumer("sample.data", message -> {
            log.info("[Worker] Consuming data in {}", Thread.currentThread().getName());
            String body = message.body();
            message.reply(body.toUpperCase());
        });
    }
}
