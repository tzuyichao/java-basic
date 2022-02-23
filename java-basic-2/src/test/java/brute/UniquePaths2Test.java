package brute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class UniquePaths2Test {
    @Test
    public void happy1() {
        var solver = new UniquePaths2();
        assertThat(solver.uniquePathsWithObstacles(new int[][] {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}}), is(equalTo(2)));
    }

    @Test
    public void happy2() {
        var solver = new UniquePaths2();
        assertThat(solver.uniquePathsWithObstacles(new int[][] {{0, 1}, {0, 0}}), is(equalTo(1)));
    }

    @Test
    public void happy3() {
        var solver = new UniquePaths2();
        assertThat(solver.uniquePathsWithObstacles(new int[][] {{0}}), is(equalTo(1)));
    }

    @Test
    public void happy4() {
        var solver = new UniquePaths2();
        assertThat(solver.uniquePathsWithObstacles(new int[][] {{1}}), is(equalTo(0)));
    }

    @Test
    public void happy5() {
        var solver = new UniquePaths2();
        assertThat(solver.uniquePathsWithObstacles(new int[][] {{0, 0, 0}, {0, 1, 0}, {0, 0, 1}}), is(equalTo(0)));
    }

    @Test
    public void happy6() {
        var solver = new UniquePaths2();
        assertThat(solver.uniquePathsWithObstacles(new int[][] {{1, 0, 0}, {0, 1, 0}, {0, 0, 0}}), is(equalTo(0)));
    }

    @Test
    public void happy7() {
        var solver = new UniquePaths2();
        assertThat(solver.uniquePathsWithObstacles(new int[][] {{1, 0}}), is(equalTo(0)));
    }
}
