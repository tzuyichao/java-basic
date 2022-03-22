package brute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ZigzagConversionTest {
    @Test
    public void happy1() {
        var solver = new ZigzagConversion();
        assertThat(solver.convert("PAYPALISHIRING", 3), is(equalTo("PAHNAPLSIIGYIR")));
    }

    @Test
    public void happy2() {
        var solver = new ZigzagConversion();
        assertThat(solver.convert("PAYPALISHIRING", 4), is(equalTo("PINALSIGYAHRPI")));
    }

    @Test
    public void happy3() {
        var solver = new ZigzagConversion();
        assertThat(solver.convert("A", 1), is(equalTo("A")));
    }

    @Test
    public void happy4() {
        var solver = new ZigzagConversion();
        assertThat(solver.convert("A", 4), is(equalTo("A")));
    }

    @Test
    public void happy5() {
        var solver = new ZigzagConversion();
        assertThat(solver.convert("PAYPALISHIRING", 2), is(equalTo("PYAIHRNAPLSIIG")));
    }

    @Test
    public void happy6() {
        var solver = new ZigzagConversion();
        assertThat(solver.convert("PAYPALISHIRING", 1), is(equalTo("PAYPALISHIRING")));
    }
}
