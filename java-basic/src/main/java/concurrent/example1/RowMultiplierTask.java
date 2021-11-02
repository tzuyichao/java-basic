package concurrent.example1;

public class RowMultiplierTask implements Runnable {
    private final double[][] matrix1;
    private final double[][] matrix2;
    private final double[][] result;
    private final int row;

    public RowMultiplierTask(double[][] matrix1, double[][] matrix2, double[][] result, int row) {
        this.matrix1 = matrix1;
        this.matrix2 = matrix2;
        this.result = result;
        this.row = row;
    }

    @Override
    public void run() {
        for(int j=0; j<matrix2[0].length; j++) {
            result[row][j] = 0;
            for(int k=0; k<matrix1[row].length; k++) {
                result[row][j] += matrix1[row][k] * matrix2[k][j];
            }
        }
    }
}
