package lang.filter;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Benchmark                      Mode  Cnt    Score    Error  Units
 * BenchmarkMain.filterByReplace  avgt   25  253.465 ±  9.154  ns/op
 * BenchmarkMain.filterByTrie     avgt   25  310.084 ± 14.647  ns/op
 */
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class BenchmarkMain {
    String str = "你好, 小狗, 小貓, 今天天氣真的很好";
    String mask = "***";
    List<String> keys = Arrays.asList("豬狗", "萬神殿", "小狗", "北歐", "小貓", "小鵝", "垃圾", "諸神黃昏", "挪威"
            , "索爾", "永和", "服装", "饰品", "有限", "公司", "超級", "戰神", "瑪卡", "核心", "勇士", "專利", "德國"
            , "透納葉", "調節");
    NameFilter replaceFilter = new ReplaceFilter(keys);
    TrieKeywordFilter trieKeywordFilter = new TrieKeywordFilter(keys);

    @Benchmark
    public void filterByReplace() {
        replaceFilter.filter(str, mask);
    }

    @Benchmark
    public void filterByTrie() {
        trieKeywordFilter.filter(str, mask);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(BenchmarkMain.class.getSimpleName())
                .build();
        new Runner(opt).run();
    }
}
