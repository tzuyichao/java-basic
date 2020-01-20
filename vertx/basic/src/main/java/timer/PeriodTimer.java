package timer;

import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * from https://www.codingame.com/playgrounds/1676/scheduling-tasks-with-eclipse-vert-x/delayed-tasks
 */
@Slf4j
public class PeriodTimer {
    public static void main(String[] args) {
        System.out.println(TimeUnit.HOURS.toMillis(1));
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
