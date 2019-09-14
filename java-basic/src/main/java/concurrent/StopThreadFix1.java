package concurrent;

import java.util.concurrent.TimeUnit;

public class StopThreadFix1 {
    private static volatile boolean isStopRequested;

    public static void main(String[] args) throws InterruptedException {
        Thread backgroundThread = new Thread(() -> {
            int i=0;
            while(!isStopRequested) {
                i ++;
            }
            System.out.println("Count Thread Stop.");
        });

        backgroundThread.start();
        TimeUnit.SECONDS.sleep(1);

        isStopRequested = true;
    }
}
