package brute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

public class DecodeWaysTest {
    @Test
    public void happy_1() {
        var solver = new DecodeWays();
        assertThat(solver.numDecodings("12"), is(equalTo(2)));
    }

    @Test
    public void happy_2() {
        var solver = new DecodeWays();
        assertThat(solver.numDecodings("226"), is(equalTo(3)));
    }

    @Test
    public void happy_3() {
        var solver = new DecodeWays();
        assertThat(solver.numDecodings("06"), is(equalTo(0)));
    }
}
