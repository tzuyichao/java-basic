package brute;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MinimumPathSumTest {
    @Test
    public void happy_1() {
        MinimumPathSum solver = new MinimumPathSum();
        assertThat(solver.minPathSum(new int[][] {{1, 3, 1}, {1, 5, 1}, {4, 2, 1}}))
                .isEqualTo(7);
    }

    @Test
    public void happy_2() {
        MinimumPathSum solver = new MinimumPathSum();
        assertThat(solver.minPathSum(new int[][] {{1, 2, 3}, {4, 5, 6}}))
                .isEqualTo(12);
    }

    @Test
    public void happy_4() {
        MinimumPathSum solver = new MinimumPathSum();
        assertThat(solver.minPathSum(new int[][] {{1}}))
                .isEqualTo(1);
    }
}
