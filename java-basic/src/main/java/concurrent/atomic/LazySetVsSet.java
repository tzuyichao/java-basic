package concurrent.atomic;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * from 汪文君的書
 */
@Measurement(iterations = 10)
@Warmup(iterations = 10)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class LazySetVsSet {
    private AtomicInteger atomicInteger;

    @Setup(Level.Iteration)
    public void setup() {
        this.atomicInteger = new AtomicInteger(0);
    }

    @Benchmark
    public void testSet() {
        this.atomicInteger.set(10);
    }

    @Benchmark
    public void testLazySet() {
        this.atomicInteger.lazySet(10);
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(LazySetVsSet.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(options).run();
    }
}
