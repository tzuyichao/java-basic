package chapter2.hello;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;

@Slf4j
public class MixThreading extends AbstractVerticle {
    @Override
    public void start() throws Exception {
        Context context = vertx.getOrCreateContext();
        new Thread(() -> {
            try {
                run(context);
            } catch (InterruptedException e) {
                log.error("Woops", e);
            }
        }).start();
    }

    private void run(Context context) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        log.info("I'm in a non-Vert.x thread");
        context.runOnContext(v -> {
            log.info("I'm on the event-loop");
            vertx.setTimer(1000, id -> {
                log.info("This is the final countdown");
                latch.countDown();
            });
        });
        log.info("Waiting on countdown latch...");
        latch.await();
        log.info("Bye!");
    }

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MixThreading());
    }
}
