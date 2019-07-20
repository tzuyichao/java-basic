package concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockLab1 {
    private static final Logger logger = LoggerFactory.getLogger(ReadWriteLockLab1.class);

    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static Deque<Integer> data = new LinkedList<>();
    private static int value;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for(int i=0; i<5; i++) {
            executorService.execute(() -> {
                for(int idx=0; idx<10; idx++) {
                    lock.writeLock().lock();
                    try {
                        data.addFirst(++value);
                    } finally {
                        lock.writeLock().unlock();
                    }
                }
            });
            executorService.execute(() -> {
                for(int idx=0; idx<10; idx++) {
                    Integer v = null;
                    do {
                        lock.readLock().lock();
                        try {
                            v = data.pollLast();
                            if(null == v) {
                                Thread.yield();
                            } else {
                                logger.info("{}", v);
                                break;
                            }
                        } finally {
                            lock.readLock().unlock();
                        }
                    } while(true);
                }
            });
        }
        executorService.shutdown();
        executorService.awaitTermination(10L, TimeUnit.MILLISECONDS);
    }
}
