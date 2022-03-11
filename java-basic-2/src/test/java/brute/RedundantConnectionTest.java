package brute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RedundantConnectionTest {
    @Test
    public void happy1() {
        var solver = new RedundantConnection();
        assertThat(solver.findRedundantConnection(new int[][] {{1,2}, {1,3}, {2,3}}), is(equalTo(new int[]{2, 3})));
    }

    @Test
    public void happy2() {
        var solver = new RedundantConnection();
        assertThat(solver.findRedundantConnection(new int[][] {{1,2}, {2,3}, {3,4}, {1,4}, {1,5}}), is(equalTo(new int[]{1, 4})));
    }

    @Test
    public void happy3() {
        var solver = new RedundantConnection();
        assertThat(solver.findRedundantConnection(new int[][] {{1,5}, {3,4}, {3,5}, {4,5}, {2,4}}), is(equalTo(new int[]{4, 5})));
    }
}
