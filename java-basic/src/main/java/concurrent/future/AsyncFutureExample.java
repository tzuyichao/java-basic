package concurrent.future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

@Slf4j
public class AsyncFutureExample {
    public static String doSomethingA() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("--- doSomeThingA ---");
        return "TaskAResult";
    }

    public static String doSomethingB() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("--- doSomeThingB ---");
        return "TaskBResult";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();
        FutureTask<String> futureWork = new FutureTask<String>(()-> {
            String result = null;
            try {
                result = doSomethingA();
            } catch(Exception e) {
                e.printStackTrace();
            }
            return result;
        });

        Thread thread = new Thread(futureWork, "threadA");
        thread.start();

        String taskBResult = doSomethingB();

        String taskAResult = futureWork.get();

        log.info("taskAResult: {}", taskAResult);
        log.info("taskBResult: {}", taskBResult);
        log.info("Time: {}", (System.currentTimeMillis() - start));
    }
}
