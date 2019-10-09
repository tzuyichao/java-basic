package concurrent;

import java.util.concurrent.*;

public class ScheduleLab {
    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
        final Future<String> handler = executorService.submit(() -> {
                System.out.println("done.");
                return "handler done.";
        });
        executorService.scheduleAtFixedRate(() -> {
            System.out.println(System.currentTimeMillis() + " schedule thread do");
//            handler.cancel(true);
        }, 10000, 10000, TimeUnit.MILLISECONDS);
        String result = handler.get(60, TimeUnit.SECONDS);
        System.out.println(result);
        TimeUnit.MINUTES.sleep(1);
        executorService.shutdown();
    }
}
