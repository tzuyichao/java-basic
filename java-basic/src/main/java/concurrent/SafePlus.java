package concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public final class SafePlus {
    public static final int MAX_TURN = 1000000;
    static class SafeCounter implements Runnable {
        private AtomicInteger amount = new AtomicInteger(0);

        public void increase() {
            amount.incrementAndGet();
        }

        @Override
        public void run() {
            int turn = 0;
            while(turn < MAX_TURN) {
                ++turn;
                increase();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SafePlus.SafeCounter counter = new SafePlus.SafeCounter();
        for(int i=0; i<10; i++) {
            Thread thread = new Thread(counter);
            thread.start();
        }
        TimeUnit.SECONDS.sleep(2);
        log.info("Expect: {}", MAX_TURN * 10);
        log.info("Actual: {}", counter.amount);
        log.info("Diff: {}", (MAX_TURN * 10 - counter.amount.get()));
    }
}
