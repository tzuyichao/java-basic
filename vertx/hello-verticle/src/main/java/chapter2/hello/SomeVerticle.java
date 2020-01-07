package chapter2.hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SomeVerticle extends AbstractVerticle {
    @Override
    public void start(Promise<Void> Promise) throws Exception {
        vertx.createHttpServer()
                .requestHandler(req -> {
                    log.info("called");
                    req.response().end("Done.");
                })
                .listen(8088, ar-> {
                    if(ar.succeeded()) {
                        log.info("bind success: {}", Promise);
                        Promise.complete();
                    } else {
                        log.error("bind failed: {}", Promise);
                        Promise.fail(ar.cause());
                    }
                });
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new SomeVerticle());
    }
}
