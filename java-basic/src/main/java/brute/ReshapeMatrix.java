package brute;

/**
 * = 566
 */
public class ReshapeMatrix {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        if(nums.length * nums[0].length != r * c) {
            return nums;
        }
        int[][] result = new int[r][c];

        int n = nums[0].length;
        for(int i=0; i<nums.length; i++) {
            for(int j=0; j<nums[0].length; j++) {
                int pos = i*n+j;
                result[pos/c][pos%c] = nums[i][j];
            }
        }

        return result;
    }
}
