package concurrent.example1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParallelGroupMultiplierTask {
    public static void multiply(double[][] matrix1, double[][] matrix2, double[][] result) {
        List<Thread> threads = new ArrayList<>();
        int rows1 =matrix1.length;
        int numThread = Runtime.getRuntime().availableProcessors();
        int startIndex, endIndex, step;
        step = rows1 / numThread;
        startIndex = 0;
        endIndex = step;
        for(int i=0; i<numThread; i++) {
            GroupMultiplierTask task = new GroupMultiplierTask(matrix1, matrix2, result, startIndex, endIndex);
            Thread thread = new Thread(task);
            thread.start();
            threads.add(thread);
            startIndex = step;
            endIndex = i==numThread-2?rows1:endIndex+step;
        }
        for(Thread thread: threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        double[][] matrix1 = MatrixGenerator.generate(2000, 2000);
        double[][] matrix2 = MatrixGenerator.generate(2000, 2000);
        double[][] result = new double[matrix1.length][matrix2[0].length];

        Date start = new Date();
        ParallelGroupMultiplierTask.multiply(matrix1, matrix2, result);
        Date end = new Date();
        System.out.printf("Thread number depends on processor: %d%n", end.getTime()-start.getTime());
    }
}
