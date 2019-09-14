package concurrent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class WhyMutexTest {

    @Test
    public void givenSemaphoreSequenceGenerator_whenRaceCondition() throws Exception {
        int count = 1000;
        Set<Integer> uniqueSequences = getUniqueSequences(new SequenceGeneratorUsingSemaphore(), count);
        assertEquals(count, uniqueSequences.size());
    }

    @Test
    public void givenReentrantLockSequenceGenerator_whenRaceCondition() throws Exception {
        int count = 1000;
        Set<Integer> uniqueSequences = getUniqueSequences(new SequenceGeneratorUsingReentrantLock(), count);
        assertEquals(count, uniqueSequences.size());
    }

    @Test
    public void givenSynchronizedBlockSequenceGenerator_whenRaceCondition() throws Exception {
        int count = 1000;
        Set<Integer> uniqueSequences = getUniqueSequences(new SequenceGeneratorUsingSynchronizedBlock(), count);
        assertEquals(count, uniqueSequences.size());
    }

    @Test
    public void givenSynchronizedMethodSequenceGenerator_whenRaceCondition() throws Exception {
        int count = 1000;
        Set<Integer> uniqueSequences = getUniqueSequences(new SequenceGeneratorUsingSynchronizedMethod(), count);
        assertEquals(count, uniqueSequences.size());
    }

    @Test
    public void givenUnsafeSequenceGenerator_whenRaceCondition_thenUnexpectedBehavior() throws Exception {
        int count = 1000;
        Set<Integer> uniqueSequences = getUniqueSequences(new SequenceGenerator(), count);
        assertNotEquals(count, uniqueSequences.size());
    }

    private Set<Integer> getUniqueSequences(SequenceGenerator sequenceGenerator, int count) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Set<Integer> uniqueSequence = new LinkedHashSet<>();
        List<Future<Integer>> futures = new ArrayList<>();

        for(int i=0; i<count; i++) {
            futures.add(executorService.submit(sequenceGenerator::getNextSequence));
        }

        for(Future<Integer> future: futures) {
            uniqueSequence.add(future.get());
        }

        executorService.awaitTermination(1, TimeUnit.SECONDS);
        executorService.shutdown();

        return uniqueSequence;
    }
}
