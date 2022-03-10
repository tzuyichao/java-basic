package brute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class IntegerReplacementTest {
    @Test
    public void happy1() {
        var solver = new IntegerReplacement();
        assertThat(solver.integerReplacement(8), is(equalTo(3)));
    }

    @Test
    public void happy2() {
        var solver = new IntegerReplacement();
        assertThat(solver.integerReplacement(7), is(equalTo(4)));
    }

    @Test
    public void happy3() {
        var solver = new IntegerReplacement();
        assertThat(solver.integerReplacement(4), is(equalTo(2)));
    }

    @Test
    public void happy4() {
        var solver = new IntegerReplacement();
        assertThat(solver.integerReplacement(1), is(equalTo(0)));
    }

    @Test
    public void happy5() {
        var solver = new IntegerReplacement();
        assertThat(solver.integerReplacement(65535), is(equalTo(17)));
    }

    @Test
    public void happy6() {
        var solver = new IntegerReplacement();
        assertThat(solver.integerReplacement(3), is(equalTo(2)));
    }

    @Test
    public void happy7() {
        var solver = new IntegerReplacement();
        assertThat(solver.integerReplacement(2147483647), is(equalTo(2)));
    }
}
