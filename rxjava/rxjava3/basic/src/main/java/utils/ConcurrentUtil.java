package utils;

import java.util.concurrent.TimeUnit;

public final class ConcurrentUtil {
    public static void sleep(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
