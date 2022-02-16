package brute;

/**
 * 48. Rotate Image
 */
public interface RotateImage {
    void rotate(int[][] matrix);
    enum Type {
        Basic
    }

    static RotateImage getInstance(Type type) {
        switch(type) {
            case Basic:
                return new BasicRotateImage();
            default:
                throw new IllegalArgumentException("type not support");
        }
    }
}

/**
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Rotate Image.
 * Memory Usage: 40.9 MB, less than 31.35% of Java online submissions for Rotate Image.
 */
class BasicRotateImage implements RotateImage {

    public void rotate(int[][] matrix) {
        var n = matrix.length;
        if(1 == n) {
            return;
        }
        for(int i=0; i<n/2; i++) {
            for(int j=i; j<n-1-i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-1-j][i];
                matrix[n-1-j][i] = matrix[n-1-i][n-1-j];
                matrix[n-1-i][n-1-j] = matrix[j][n-1-i];
                matrix[j][n-1-i] = temp;
            }
        }
    }
}
