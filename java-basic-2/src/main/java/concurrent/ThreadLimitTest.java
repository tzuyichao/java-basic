package concurrent;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

/**
 * Example from Modern Concurrency in Java
 */
public class ThreadLimitTest {
    public static void main(String[] args) {
        AtomicInteger count = new AtomicInteger(0);
        try {
            while(true) {
                Thread thread = new Thread(() -> {
                    count.incrementAndGet();
                    LockSupport.park();
                });
                thread.start();
            }
        } catch (OutOfMemoryError error) {
            System.out.printf("Reached thread limit: %d%n", count.get());
            error.printStackTrace();
        }
    }
}
