package lock;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Source: https://shipilev.net/jvm/anatomy-quarks/1-lock-coarsening-for-loops/
 *
 * https://hackmd.io/CJeY1bnfR1m97iaI5v4yMA#Lock-Coarsening-and-Loops
 */
@Warmup(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(value = 1, jvmArgsPrepend = {"-XX:-UseBiasedLocking"})
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class LockRoach {
    int x;

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public void test() {
        for(int c=0; c<1000; c++) {
            synchronized (this) {
                x += 0x42;
            }
        }
    }
}
