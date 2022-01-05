package concurrent.forkjoin;

import lombok.extern.java.Log;

import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.RecursiveTask;

@Log
public class Task<Integer> extends RecursiveTask<Integer> {
    private static final short UNVISITED = 0;
    private static final short VISITED = 1;

    private final Set<Task<Integer>> dependencies;
    private final String name;
    private final Callable<Integer> callable;

    public Task(String name, Callable<Integer> callable, Task<Integer>... dependencies) {
        this.name = name;
        this.callable = callable;
        this.dependencies = Set.of(dependencies);
    }

    @Override
    protected Integer compute() {
        dependencies.stream()
                .filter((task) -> task.updateTaskAsVisited())
                .forEachOrdered((task) -> {
                    log.info(() -> "Tagged: " + task + "(" + task.getForkJoinTaskTag() + ")");
                    task.fork();
                });
        for(Task task : dependencies) {
            task.join();
        }

        try {
            return callable.call();
        } catch (Exception e) {
            log.severe(() -> "Exception: " + e);
        }
        return null;
    }

    public boolean updateTaskAsVisited() {
        return compareAndSetForkJoinTaskTag(UNVISITED, VISITED);
    }

    @Override
    public String toString() {
        return name + " | dependencies=" + dependencies + "}";
    }
}
