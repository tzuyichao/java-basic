package concurrent;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchLab {
    static class Worker implements Runnable {
        CountDownLatch countDownLatch;

        public Worker(CountDownLatch countDownLatch) {
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            if(Math.random() < 0.8) {
                countDownLatch.countDown();
            } else {
                System.out.println(Thread.currentThread().getName() + " Exception Boom!");
            }
        }
    }

    static void run() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for(int i=0; i<10; i++) {
            Thread thread = new Thread(new Worker(countDownLatch));
            System.out.println("Creating Thread " + thread.getName());
            thread.start();
        }

        countDownLatch.await();
        System.out.println("Done");
    }

    public static void main(String[] args) throws InterruptedException {
        run();
    }
}
