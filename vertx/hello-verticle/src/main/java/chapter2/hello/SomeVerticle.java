package chapter2.hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SomeVerticle extends AbstractVerticle {
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        vertx.createHttpServer()
                .requestHandler(req -> {
                    log.info("called");
                    req.response().end("Done.");
                })
                .listen(8088, ar-> {
                    if(ar.succeeded()) {
                        log.info("bind success");
                        startFuture.complete();
                    } else {
                        log.error("bind failed");
                        startFuture.fail(ar.cause());
                    }
                });
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new SomeVerticle());
    }
}
