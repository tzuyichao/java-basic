package timer;

import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class PeriodTimer {
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        log.info("Running");
        vertx.setTimer(TimeUnit.SECONDS.toMillis(5), id -> {
            log.info("id: {}", id);
            log.info("Hello");
        });
        vertx.setTimer(2000, id -> {
            log.info("id: {}", id);
            log.info("tick");
        });
    }
}
