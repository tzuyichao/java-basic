package jmh;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * from https://juejin.cn/post/7086443387636678670
 */
@BenchmarkMode({Mode.Throughput})
@OutputTimeUnit(TimeUnit.SECONDS)
@State(Scope.Thread)
@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(6)
public class ObjectMapperTest {
    String json = "{ \"color\" : \"Black\", \"type\" : \"BMW\" }";
    @State(Scope.Benchmark)
    public static class BenchmarkState {
        ObjectMapper GLOBAL_OBJECT_MAPPER = new ObjectMapper();
        ThreadLocal<ObjectMapper> GLOBAL_OBJECT_MAPPER_THREAD = new ThreadLocal<>();
    }

    @Benchmark
    public Map globalTest(BenchmarkState state) throws JsonProcessingException {
        Map map = state.GLOBAL_OBJECT_MAPPER.readValue(json, Map.class);
        return map;
    }

    @Benchmark
    public Map globalTestThreadLocal(BenchmarkState state) throws JsonProcessingException {
        if(null == state.GLOBAL_OBJECT_MAPPER_THREAD.get()) {
            state.GLOBAL_OBJECT_MAPPER_THREAD.set(new ObjectMapper());
        }
        Map map = state.GLOBAL_OBJECT_MAPPER_THREAD.get().readValue(json, Map.class);
        return map;
    }

    @Benchmark
    public Map localTest() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.readValue(json, Map.class);
        return map;
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(ObjectMapperTest.class.getSimpleName())
                .resultFormat(ResultFormatType.CSV)
                .build();
        new Runner(options).run();
    }
}
