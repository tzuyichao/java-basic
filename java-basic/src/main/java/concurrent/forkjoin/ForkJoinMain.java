package concurrent.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.logging.Logger;

public class ForkJoinMain {
    private static final Logger logger = Logger.getLogger(ForkJoinMain.class.getName());
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();

        Random rnd = new Random();
        List<Integer> list = new ArrayList<>();

        for(int i=0; i<200; i++) {
            list.add(1 + rnd.nextInt(10));
        }

        SumRecursiveTask sumRecursiveTask = new SumRecursiveTask(list);
        Integer sumAll = forkJoinPool.invoke(sumRecursiveTask);

        logger.info(() -> "Final Sum: " + sumAll);
    }
}
