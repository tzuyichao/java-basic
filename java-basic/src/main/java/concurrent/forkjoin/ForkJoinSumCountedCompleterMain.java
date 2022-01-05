package concurrent.forkjoin;

import lombok.extern.java.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

@Log
public class ForkJoinSumCountedCompleterMain {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = ForkJoinPool.commonPool();
        Random rnd = new Random();
        List<Integer> list = new ArrayList<>();

        for(int i=0; i<200; i++) {
            list.add(1+rnd.nextInt(10));
        }

        SumCountedCompleter sumCountedCompleter = new SumCountedCompleter(null, list);
        forkJoinPool.invoke(sumCountedCompleter);

        log.info(() -> "Done! Result: " + sumCountedCompleter.getRawResult());
    }
}
