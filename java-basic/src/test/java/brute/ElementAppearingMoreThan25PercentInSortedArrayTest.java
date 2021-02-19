package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ElementAppearingMoreThan25PercentInSortedArrayTest {
    ElementAppearingMoreThan25PercentInSortedArray solver;

    @Before
    public void init() {
        solver = new ElementAppearingMoreThan25PercentInSortedArray();
    }

    @Test
    public void simple1() {
        assertEquals(6, solver.findSpecialInteger(new int[] {1, 2, 2, 6, 6, 6, 6, 7, 10}));
    }

    @Test
    public void simple2() {
        assertEquals(6, solver.findSpecialInteger(new int[] {6}));
    }

    @Test
    public void simple3() {
        assertEquals(6, solver.findSpecialInteger(new int[] {6, 6}));
    }

    @Test
    public void simple4() {
        assertEquals(6, solver.findSpecialInteger(new int[] {1, 2, 6, 6}));
    }

    @Test
    public void simple5() {
        assertEquals(6, solver.findSpecialInteger(new int[] {1, 6, 6}));
    }

    @Test
    public void simple6() {
        assertEquals(6, solver.findSpecialInteger(new int[] {6, 6, 7}));
    }
}
