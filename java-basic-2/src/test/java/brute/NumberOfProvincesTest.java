package brute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class NumberOfProvincesTest {
    @Test
    public void happy1() {
        var solver = new NumberOfProvinces();
        assertThat(solver.findCircleNum(new int[][] {{1,1,0}, {1,1,0}, {0,0,1}}), is(equalTo(2)));
    }

    @Test
    public void happy2() {
        var solver = new NumberOfProvinces();
        assertThat(solver.findCircleNum(new int[][] {{1,0,0}, {0,1,0}, {0,0,1}}), is(equalTo(3)));
    }
}
