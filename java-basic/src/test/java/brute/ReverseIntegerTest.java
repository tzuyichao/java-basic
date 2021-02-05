package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReverseIntegerTest {
    ReverseInteger solver;

    @Before
    public void init() {
        solver = new ReverseInteger();
    }

    @Test
    public void simple1() {
        assertEquals(321, solver.reverse(123));
    }

    @Test
    public void simple2() {
        assertEquals(-321, solver.reverse(-123));
    }

    @Test
    public void simple3() {
        assertEquals(21, solver.reverse(120));
    }

    @Test
    public void simple4() {
        assertEquals(0, solver.reverse(0));
    }

    @Test
    public void simple5() {
        assertEquals(0, solver.reverse(1534236469));
    }

}
