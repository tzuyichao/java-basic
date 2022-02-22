package brute;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UniquePathsTest {
    @Test
    public void happy1() {
        UniquePaths solver = new UniquePaths();
        assertThat(solver.uniquePaths(10, 10))
                .isEqualTo(48620);
    }

    @Test
    public void happy2() {
        UniquePaths solver = new UniquePaths();
        assertThat(solver.uniquePaths(9, 9))
                .isEqualTo(12870);
    }

    @Test
    public void happy3() {
        UniquePaths solver = new UniquePaths();
        assertThat(solver.uniquePaths(1, 1))
                .isEqualTo(1);
    }
}
