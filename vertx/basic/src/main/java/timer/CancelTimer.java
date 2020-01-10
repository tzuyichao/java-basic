package timer;

import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * from https://www.codingame.com/playgrounds/1676/scheduling-tasks-with-eclipse-vert-x/cancelling-timers
 */
@Slf4j
public class CancelTimer {
    private static int counter = 0;
    public static void main(String[] args) throws InterruptedException {
        Vertx vertx = Vertx.vertx();

        long timerId = vertx.setPeriodic(1000, id -> {
            log.info("id: {}", id);
            log.info("Hello {}", ++counter);
            if(counter == 10) {
                log.info("Cancelling Timer");
                vertx.cancelTimer(id);
            }
        });
        log.info("timerId: {}", timerId);
        TimeUnit.SECONDS.sleep(15);
        vertx.close(voidAsyncResult -> log.info("vert.x shutdown"));
    }
}
