package lang;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * Sample from Java系統性能優化實戰
 *
 * Benchmark                            Mode  Cnt    Score   Error  Units
 * StringSearchBenchmark.compileSearch  avgt   25  107.672 ± 3.950  ns/op
 * StringSearchBenchmark.contain        avgt   25    8.996 ± 0.040  ns/op
 * StringSearchBenchmark.search         avgt   25  305.901 ± 3.357  ns/op
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class StringSearchBenchmark {
    private String str = "你好.java";
    private String reg = ".*java.*";
    private String key = "java";
    private Pattern pattern = Pattern.compile(reg);

    @Benchmark
    public boolean search() {
        return str.matches(reg);
    }

    @Benchmark
    public boolean compileSearch() {
        return pattern.matcher(str).matches();
    }

    @Benchmark
    public boolean contain() {
        return str.contains(key);
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(StringSearchBenchmark.class.getSimpleName())
                .build();
        new Runner(options).run();
    }
}
