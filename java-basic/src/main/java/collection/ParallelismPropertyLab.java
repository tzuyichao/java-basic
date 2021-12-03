package collection;

import java.util.concurrent.ForkJoinPool;

public class ParallelismPropertyLab {
    public static void main(String[] args) {
        System.out.println("Fork Join Pool parallelism: " + ForkJoinPool.commonPool().getParallelism());
        System.out.println("java.util.concurrent.ForkJoinPool.common.parallelism: " + System.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism"));
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "12");
        System.out.println("Fork Join Pool parallelism: " + ForkJoinPool.commonPool().getParallelism());
        System.out.println("java.util.concurrent.ForkJoinPool.common.parallelism: " + System.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism"));
    }
}
