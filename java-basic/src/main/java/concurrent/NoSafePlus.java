package concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * from https://www.cnblogs.com/crazymakercircle/p/10089113.html
 */
@Slf4j
public final class NoSafePlus {
    public static final int MAX_TURN = 1000000;

    static class NoSafeCounter implements Runnable {
        private int amount = 0;

        public void increase() {
            amount++;
        }

        @Override
        public void run() {
            int turn = 0;
            while(turn < MAX_TURN) {
                ++turn;
                //synchronized (this) {
                    increase();
                //}
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NoSafeCounter counter = new NoSafeCounter();
        for(int i=0; i<10; i++) {
            Thread thread = new Thread(counter);
            thread.start();
        }
        TimeUnit.SECONDS.sleep(2);
        log.info("Expect: {}", MAX_TURN * 10);
        log.info("Actual: {}", counter.amount);
        log.info("Diff: {}", (MAX_TURN * 10 - counter.amount));
    }
}
