package concurrent;

import java.util.concurrent.*;

/**
 * https://examples.javacodegeeks.com/core-java/util/concurrent/exchanger/java-util-concurrent-exchanger-example/
 */
public class ExchangerExample {
    private Exchanger<String> exchanger = new Exchanger<>();
    private ExecutorService executorService = Executors.newFixedThreadPool(2);

    private class Producer implements Runnable {
        private String queue;
        private String name;

        public Producer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                queue = exchanger.exchange("Ready Queue");
                System.out.println(this.name + ":" + Thread.currentThread().getName() + " now has " + queue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private class Consumer implements Runnable {
        private String queue;
        private String name;

        public Consumer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                queue = exchanger.exchange("Empty Queue");
                System.out.println(this.name + ":" + Thread.currentThread().getName() + " now has " + queue);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void start() {
        executorService.submit(new Producer("Producer"));
        executorService.submit(new Consumer("Consumer"));
    }

    private void shutdown() {
        executorService.shutdown();
    }

    public static void main(String[] args) {
        ExchangerExample exchangerExample = new ExchangerExample();
        exchangerExample.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            exchangerExample.shutdown();
        }
    }
}
