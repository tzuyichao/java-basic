package concurrent.example2;

import concurrent.atomic.LazySetVsSet;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.io.File;

@BenchmarkMode({Mode.AverageTime})
public class MyBenchmark {

    @Benchmark
    public void testSerialFileSearchHosts() {
        SerialFileSearch.searchFiles(new File("C:/"), "hosts", new Result());
    }

    @Benchmark
    public void testSerialFileSearchNotExist() {
        SerialFileSearch.searchFiles(new File("C:/"), "yyy.yyy", new Result());
    }

    @Benchmark
    public void testParallelFileSearchHosts() {
        ParallelGroupFileSearch.searchFiles(new File("C:/"), "hosts", new Result());
    }

    @Benchmark
    public void testParallelFileSearchNotExist() {
        ParallelGroupFileSearch.searchFiles(new File("C:/"), "yyy.yyy", new Result());
    }

    public static void main(String[] args) throws RunnerException {
        final Options options = new OptionsBuilder()
                .include(MyBenchmark.class.getSimpleName())
                .forks(1)
                .build();
        new Runner(options).run();
    }
}
