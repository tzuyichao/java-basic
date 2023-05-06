package concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchLab {
    static class Worker implements Runnable {
        CountDownLatch countDownLatch;

        public Worker(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(Math.random() < 0.8) {
                countDownLatch.countDown();
            } else {
                System.out.println(Thread.currentThread().getName() + " Exception Boom!");
            }
        }
    }

    /**
     * 監看Worker的狀態
     */
    static class Monitor implements Runnable {
        Thread[] pool;
        public Monitor(Thread[] pool) {
            this.pool = pool;
        }

        @Override
        public void run() {
            while(true) {
                for (Thread thread : pool) {
                    System.out.println(thread.getName() + " State: " + thread.getState());
                }
                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    static void run() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        Thread[] pool = new Thread[10];

        for(int i=0; i<10; i++) {
            Thread thread = new Thread(new Worker(countDownLatch));
            pool[i] = thread;
            System.out.println("Creating Thread " + thread.getName());
            thread.start();
        }
        Thread monitor = new Thread(new Monitor(pool));
        monitor.setDaemon(true);
        monitor.start();

        // 如果有一個thread發生意料之外的狀況沒有countDown()就停止等情況這裡就會等到天荒地老
        // countDownLatch.await();
        countDownLatch.await(1, TimeUnit.MINUTES);
        System.out.println("Done");
    }

    public static void main(String[] args) throws InterruptedException {
        run();
    }
}
