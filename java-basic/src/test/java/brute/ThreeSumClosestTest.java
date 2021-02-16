package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ThreeSumClosestTest {
    ThreeSumClosest solver;
    @Before
    public void init() {
        solver = new ThreeSumClosest();
    }

    @Test
    public void simple1() {
        int result = solver.threeSumClosest(new int[] {-1,2,1,-4}, 1);
        assertEquals(2, result);
    }

    @Test
    public void simple2() {
        int result = solver.threeSumClosest(new int[] {-1,2,1}, 1);
        assertEquals(2, result);
    }

    @Test
    public void simple3() {
        int result = solver.threeSumClosest(new int[] {0,2,1,-3}, 1);
        assertEquals(0, result);
    }
}
