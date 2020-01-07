package chapter2.hello;

import io.vertx.core.AbstractVerticle;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EmptyVerticle extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        log.info("start");
    }

    @Override
    public void stop() throws Exception {
        log.info("stop");
    }
}
