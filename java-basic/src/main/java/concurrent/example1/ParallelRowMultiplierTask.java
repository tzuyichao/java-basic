package concurrent.example1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParallelRowMultiplierTask {
    public static void multiply(double[][] matrix1, double[][] matrix2, double[][] result) {
        List<Thread> threads = new ArrayList<>();
        int rows1 = matrix1.length;
        for(int i=0; i<rows1; i++) {
            RowMultiplierTask task = new RowMultiplierTask(matrix1, matrix2, result, i);
            Thread thread = new Thread(task);
            thread.start();
            threads.add(thread);

            if(threads.size() % 10 == 0) {
                waitForThreads(threads);
            }
        }
    }

    private static void waitForThreads(List<Thread> threads) {
        for(Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        threads.clear();
    }

    public static void main(String[] args) {
        double[][] matrix1 = MatrixGenerator.generate(2000, 2000);
        double[][] matrix2 = MatrixGenerator.generate(2000, 2000);
        double[][] result = new double[matrix1.length][matrix2[0].length];

        Date start = new Date();
        ParallelRowMultiplierTask.multiply(matrix1, matrix2, result);
        Date end = new Date();
        System.out.printf("Per Row: %d%n", end.getTime()-start.getTime());
    }
}
