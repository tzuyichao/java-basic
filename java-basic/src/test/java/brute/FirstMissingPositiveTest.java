package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FirstMissingPositiveTest {
    FirstMissingPositive solver;

    @Before
    public void init() {
        solver = new FirstMissingPositive();
    }

    @Test
    public void simple1() {
        assertEquals(3, solver.firstMissingPositive(new int[] {1,2,0}));
    }

    @Test
    public void simple2() {
        assertEquals(2, solver.firstMissingPositive(new int[] {3,4,-1,1}));
    }

    @Test
    public void simple3() {
        assertEquals(1, solver.firstMissingPositive(new int[] {7,8,9,11,12}));
    }

    @Test
    public void simple4() {
        assertEquals(1, solver.firstMissingPositive(new int[] {}));
    }

    @Test
    public void simple5() {
        assertEquals(3, solver.firstMissingPositive(new int[] {0,2,2,1,1}));
    }
}
