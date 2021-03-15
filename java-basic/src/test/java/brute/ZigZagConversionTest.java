package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ZigZagConversionTest {
    ZigZagConversion solver;
    @Before
    public void init() {
        solver = new ZigZagConversion();
    }

    @Test
    public void simple1() {
        assertEquals("PAHNAPLSIIGYIR", solver.convert("PAYPALISHIRING", 3));
    }

    @Test
    public void simple2() {
        assertEquals("PINALSIGYAHRPI", solver.convert("PAYPALISHIRING", 4));
    }

    @Test
    public void simple3() {
        assertEquals("A", solver.convert("A", 1));
    }
}
