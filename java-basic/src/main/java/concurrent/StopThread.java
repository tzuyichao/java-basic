package concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Item 78
 */
public class StopThread {
    private static boolean stopRequested;
    // never stop
    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i = 0;
            while(!stopRequested) {
                i++;
            }
        });
        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);
        stopRequested = true;
    }
}
