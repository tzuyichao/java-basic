package jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class FirstBenchmark {
    @Benchmark
    public int sleepAWhile() {
        try {
            Thread.sleep(500);
        } catch(InterruptedException e) {
        }
        return 0;
    }

    public static void main(String[] args) {
        Options opt = new OptionsBuilder()
                .include(FirstBenchmark.class.getSimpleName())
                .forks(1)
                .warmupIterations(5)
                .measurementIterations(5)
                .build();
        try {
            new Runner(opt).run();
        } catch(RunnerException e) {
            e.printStackTrace();
        }
    }
}
