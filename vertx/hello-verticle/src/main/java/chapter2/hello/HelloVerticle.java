package chapter2.hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public final class HelloVerticle extends AbstractVerticle {
    // single thread so does not need synchronized or AtomicLong
    private long counter = 0;

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        vertx.setPeriodic(5000, id -> {
            log.info("tick");
        });

        vertx.createHttpServer()
                .requestHandler(req -> {
                    log.info("Request #{} from {}", counter++, req.remoteAddress());
                    req.response().end("It works!");
                })
                .listen(8088);
        log.info("open http://localhost:8088");
    }

    public static void main(String[] args) {
        log.info("running...");
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new HelloVerticle());
    }
}
