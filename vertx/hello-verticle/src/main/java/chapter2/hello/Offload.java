package chapter2.hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Offload extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        vertx.setPeriodic(5000, id -> {
            log.info("Tick");
            vertx.executeBlocking(this::blockingCode, this::resultHandler);
        });
    }

    private void blockingCode(Promise<String> promise) {
        log.info("Blocking code running");
        try {
            TimeUnit.SECONDS.sleep(6);
            log.info("Done.");
            promise.complete("Ok");
        } catch(Exception e) {
            promise.fail(e);
        }
    }

    private void resultHandler(AsyncResult<String> asyncResult) {
        if(asyncResult.succeeded()) {
            log.info("Blocking Code Result: {}", asyncResult.result());
        } else {
            log.error("Woops: {}", asyncResult.cause());
        }
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new Offload());
    }
}
