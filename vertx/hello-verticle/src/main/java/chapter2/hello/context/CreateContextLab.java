package chapter2.hello.context;

import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class CreateContextLab {
    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();

        vertx.getOrCreateContext()
                .runOnContext(event -> log.info("AAA"));

        vertx.getOrCreateContext()
                .runOnContext(event -> log.info("111"));

        TimeUnit.SECONDS.sleep(10);

        vertx.close(event -> log.info("Vert.x shutdown completed."));
    }
}
