package concurrent.forkjoin;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

@Slf4j
public class SumRecursiveTask extends RecursiveTask<Integer> {
    private static final int THRESHOLD = 10;
    private final List<Integer> worklist;

    public SumRecursiveTask(List<Integer> worklist) {
        this.worklist = worklist;
    }

    @Override
    protected Integer compute() {
        if(worklist.size() <= THRESHOLD) {
            return partialSum(worklist);
        }
        return ForkJoinTask.invokeAll(createSubtasks())
                .stream()
                .mapToInt(ForkJoinTask::join)
                .sum();
    }

    private Integer partialSum(List<Integer> worklist) {
        return 0;
    }

    private List<SumRecursiveTask> createSubtasks() {
        return null;
    }
}
