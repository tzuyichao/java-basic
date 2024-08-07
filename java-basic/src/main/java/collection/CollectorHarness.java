package collection;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static collection.PartitioningLab.partitionPrimes;

public class CollectorHarness {
    public static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(new PrimeNumbersCollector());
    }

    public static void estimate(Runnable runnable) {
        long fastest = Long.MAX_VALUE;
        for(int i=0; i<10; i++) {
            long start = System.nanoTime();
            runnable.run();
            partitionPrimesWithCustomCollector(1_000_000);
            long duration = (System.nanoTime() - start)/1_000_000;
            if(duration < fastest) {
                fastest = duration;
            }
        }
        System.out.println("Fastest execution done in " + fastest + " msecs");
    }

    public static void main(String[] args) {
        estimate(() -> partitionPrimesWithCustomCollector(1_000_000));
        estimate(() -> partitionPrimes(1_000_000));
        System.out.println("Fork Join Sum:");
        estimate(() -> ForkJoinSumCalculator.forkJoinSum(100_000_000));
        System.out.println("Sequential Sum:");
        estimate(() -> {
            long[] numbers = LongStream.rangeClosed(0, 100_000_000).toArray();
            long sum = 0;
            for(int i=0; i<numbers.length; i++) {
                sum += numbers[i];
            }
        });
    }
}
