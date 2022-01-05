package concurrent.forkjoin;

import lombok.extern.java.Log;

import java.util.concurrent.ForkJoinPool;

@Log
public class ForkJoinFibonacciRecursiveActionMain {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        FibonacciRecursiveAction fibonacciRecursiveAction = new FibonacciRecursiveAction(12);
        forkJoinPool.invoke(fibonacciRecursiveAction);

        log.info(() -> "Fibonacci: " + fibonacciRecursiveAction.fibonacciNumber());
    }
}
