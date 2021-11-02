package concurrent.example1;

import java.util.Date;

public class SerialMain {
    public static void main(String[] args) {
        double[][] matrix1 = MatrixGenerator.generate(2000, 2000);
        double[][] matrix2 = MatrixGenerator.generate(2000, 2000);
        double[][] result = new double[matrix1.length][matrix2[0].length];

        Date start = new Date();
        SerialMultiplier.multiply(matrix1, matrix2, result);
        Date end = new Date();
        System.out.printf("Serial: %d%n", end.getTime()-start.getTime());
    }
}
