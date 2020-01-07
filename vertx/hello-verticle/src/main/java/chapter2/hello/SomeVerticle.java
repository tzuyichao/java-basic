package chapter2.hello;

import io.vertx.core.*;
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
        vertx.deployVerticle(new SomeVerticle(), new Handler<AsyncResult<String>>() {
            @Override
            public void handle(AsyncResult<String> event) {
                log.info("event: {}", event);
            }
        });
    }
}
