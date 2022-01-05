package concurrent.forkjoin;

import lombok.extern.java.Log;

import java.util.List;
import java.util.concurrent.CountedCompleter;
import java.util.concurrent.atomic.LongAdder;

@Log
public class SumCountedCompleter extends CountedCompleter<Long> {
    private static final int THRESHOLD = 10;
    private static final LongAdder sumAll = new LongAdder();
    private final List<Integer> worklist;

    public SumCountedCompleter(CountedCompleter<Long> c, List<Integer> worklist) {
        super(c);
        this.worklist = worklist;
    }

    @Override
    public void compute() {
        if(worklist.size() <= THRESHOLD) {
            partialSum(worklist);
        } else {
            int size = worklist.size();
            List<Integer> worklistLeft = worklist.subList(0, (size+1)/2);
            List<Integer> worklistRight = worklist.subList((size+1)/2, size);

            addToPendingCount(2);

            SumCountedCompleter leftTask = new SumCountedCompleter(this, worklistLeft);
            SumCountedCompleter rightTask = new SumCountedCompleter(this, worklistRight);

            leftTask.fork();
            rightTask.fork();
        }
        tryComplete();
    }

    @Override
    public void onCompletion(CountedCompleter<?> called) {
        log.info(() -> "Thread Complete: "+ Thread.currentThread().getName());
    }

    @Override
    public Long getRawResult() {
        return sumAll.sum();
    }

    private Integer partialSum(List<Integer> worklist) {
        int sum = worklist.stream()
                .mapToInt(e -> e)
                .sum();
        sumAll.add(sum);

        log.info(() -> "Partial Sum: " + worklist + " = " + sum + "\tThread: " + Thread.currentThread().getName());

        return sum;
    }
}
