package brute;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RotateImageTest {
    public RotateImage getSolver() {
        return RotateImage.getInstance(RotateImage.Type.Basic);
    }

    @Test
    public void test_happy1() {
        RotateImage solver = getSolver();
        int[][] subject = new int[][] {{1}};
        solver.rotate(subject);
        assertThat(subject)
                .isEqualTo(new int[][] {{1}});
    }

    @Test
    public void test_happy2() {
        RotateImage solver = getSolver();
        int[][] subject = new int[][] {{1, 2}, {3, 4}};
        solver.rotate(subject);
        assertThat(subject)
                .isEqualTo(new int[][] {{3, 1}, {4, 2}});
    }

    @Test
    public void test_happy3() {
        RotateImage solver = getSolver();
        int[][] subject = new int[][] {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        solver.rotate(subject);
        assertThat(subject)
                .isEqualTo(new int[][] {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}});
    }

    @Test
    public void test_happy4() {
        RotateImage solver = getSolver();
        int[][] subject = new int[][] {{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};
        solver.rotate(subject);
        assertThat(subject)
                .isEqualTo(new int[][] {{15, 13, 2, 5}, {14, 3, 4, 1}, {12, 6, 8, 9}, {16, 7, 10, 11}});
    }
}
