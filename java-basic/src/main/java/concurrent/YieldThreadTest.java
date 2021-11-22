package concurrent;

import java.util.stream.IntStream;

public class YieldThreadTest {
    public static void main(String[] args) {
        MyThread t = new MyThread();
        t.setDaemon(true);
        t.start();
        IntStream.range(0, 100)
                .forEach(elem -> {
                    System.out.println("Main: " + elem);
                });
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            while(true) {
                System.out.println("讓出Thread CPU Time");
                Thread.currentThread().yield();
            }
        }
    }
}
