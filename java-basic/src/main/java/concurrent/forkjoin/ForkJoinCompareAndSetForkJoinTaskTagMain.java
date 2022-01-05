package concurrent.forkjoin;

import lombok.extern.java.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.atomic.AtomicInteger;

@Log
public class ForkJoinCompareAndSetForkJoinTaskTagMain {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        Task<Integer> taskA = new Task("Task-A", new Adder(1));
        Task<Integer> taskB = new Task("Task-B", new Adder(2), taskA);
        Task<Integer> taskC = new Task("Task-C", new Adder(3), taskA, taskB);
        Task<Integer> taskD = new Task("Task-D", new Adder(4), taskA, taskB, taskC);

        Integer result = forkJoinPool.invoke(taskD);
        log.info("Done. result: " + result);
    }

    private static class Adder implements Callable<Integer> {
        private static final AtomicInteger result = new AtomicInteger();

        private Integer nr;

        public Adder(Integer nr) {
            this.nr = nr;
        }

        @Override
        public Integer call() throws Exception {
            log.info(() -> "Adding number: " + nr + " by thread:" + Thread.currentThread().getName());
            return result.addAndGet(nr);
        }
    }
}
