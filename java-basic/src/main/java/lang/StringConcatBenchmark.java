package lang;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

/**
 * Sample from Java系統性能優化實戰
 *
 * Mac OS X
 * Benchmark                                           Mode  Cnt    Score    Error  Units
 * StringConcatBenchmark.concat                        avgt   25   24.168 ±  0.397  ns/op
 * StringConcatBenchmark.concatByOptimizeBuilder       avgt   25   21.559 ±  0.080  ns/op
 * StringConcatBenchmark.concatWithoutOptimizeBuilder  avgt   25  169.779 ± 60.746  ns/op
 *
 * Windows
 * Benchmark                                           Mode  Cnt   Score   Error  Units
 * StringConcatBenchmark.concat                        avgt   25  28.138 ± 1.169  ns/op
 * StringConcatBenchmark.concatByOptimizeBuilder       avgt   25  26.264 ± 1.268  ns/op
 * StringConcatBenchmark.concatWithoutOptimizeBuilder  avgt   25  73.675 ± 3.200  ns/op
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class StringConcatBenchmark {
    String a = "select u.id, u.name from user u ";
    String b = " where u.id = ?";

    @Benchmark
    public String concat() {
        String c = a + b;
        return c;
    }

    @Benchmark
    public String concatWithoutOptimizeBuilder() {
        StringBuilder sb = new StringBuilder();
        sb.append(a);
        sb.append(b);
        return sb.toString();
    }

    @Benchmark
    public String concatByOptimizeBuilder() {
        String c = new StringBuilder().append(a).append(b).toString();
        return c;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(StringConcatBenchmark.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}
