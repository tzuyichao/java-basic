package brute;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@Ignore
public class FindLongestAwesomeSubstringTest {
    FindLongestAwesomeSubstring solver;

    @Before
    public void init() {
        solver = new FindLongestAwesomeSubstring();
    }

    @Test
    public void simple1() {
        assertEquals(5, solver.longestAwesome("3242415"));
    }

    @Test
    public void simple2() {
        assertEquals(1, solver.longestAwesome("12345678"));
    }

    @Test
    public void simple3() {
        assertEquals(6, solver.longestAwesome("213123"));
    }

    @Test
    public void simple4() {
        assertEquals(2, solver.longestAwesome("00"));
    }

    @Test
    public void simple5() {
        assertEquals(1, solver.longestAwesome("1"));
    }

    @Test
    public void simple6() {
        assertEquals(3, solver.longestAwesome("9498331"));
    }
}
