package brute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class InterleavingStringTest {
    @Test
    public void happy1() {
        var solver = new InterleavingString();
        assertThat(solver.isInterleave("aabcc", "dbbca", "aadbbcbcac"), is(equalTo(true)));
    }

    @Test
    public void happy2() {
        var solver = new InterleavingString();
        assertThat(solver.isInterleave("aabcc", "dbbca", "aadbbbaccc"), is(equalTo(false)));
    }

    @Test
    public void happy3() {
        var solver = new InterleavingString();
        assertThat(solver.isInterleave("", "", ""), is(equalTo(true)));
    }
}
