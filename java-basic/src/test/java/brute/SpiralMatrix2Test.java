package brute;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpiralMatrix2Test {
    @Test
    public void test_1() {
        SpiralMatrix2 solver = new SpiralMatrix2();
        assertThat(solver.generateMatrix(1))
                .isEqualTo(new int[][]{{1}});
    }

    @Test
    public void test_2() {
        SpiralMatrix2 solver = new SpiralMatrix2();
        assertThat(solver.generateMatrix(2))
                .isEqualTo(new int[][]{{1, 2}, {4, 3}});
    }

    @Test
    public void test_3() {
        SpiralMatrix2 solver = new SpiralMatrix2();
        assertThat(solver.generateMatrix(3))
                .isEqualTo(new int[][]{{1, 2, 3}, {8, 9, 4}, {7, 6, 5}});
    }
}
