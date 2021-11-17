package concurrent.forkjoin;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
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
        int sum = worklist.stream()
                .mapToInt(e -> e)
                .sum();
        return sum;
    }

    private List<SumRecursiveTask> createSubtasks() {
        List<SumRecursiveTask> subTasks = new ArrayList<>();
        int size = worklist.size();
        List<Integer> worklistLeft = worklist.subList(0, (size+1)/2);
        List<Integer> worklistRight = worklist.subList((size+1)/2, size);
        subTasks.add(new SumRecursiveTask(worklistLeft));
        subTasks.add(new SumRecursiveTask(worklistRight));
        return subTasks;
    }
}
