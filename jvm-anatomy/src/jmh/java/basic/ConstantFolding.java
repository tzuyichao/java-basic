package basic;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Source: https://www.baeldung.com/java-microbenchmark-harness
 */
@Warmup(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1)
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class ConstantFolding {
    @Benchmark
    public double foldedLog() {
        int x = 8;

        return Math.log(x);
    }

    @Benchmark
    public double foldedLog2() {
        return 2.0794415416798357;
    }

    @State(Scope.Benchmark)
    public static class Log {
        public int x = 8;
    }

    @Benchmark
    public double log(Log input) {
        return Math.log(input.x);
    }
}
