package brute;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class PascalTriangle2Test {
    PascalTriangle2 solver = new PascalTriangle2();

    @Test
    public void simple1() {
        List<Integer> result = solver.getRow(3);
        assertNotNull(result);
        assertEquals(4, result.size());
    }

    @Test
    public void simple2() {
        List<Integer> result = solver.getRow(0);
        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    public void simple3() {
        List<Integer> result = solver.getRow(1);
        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
