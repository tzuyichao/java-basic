package brute;

import org.junit.Before;
import org.junit.Test;

import static brute.AssertHelper.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MergeIntervalsTest {
    MergeIntervals solver;

    @Before
    public void init() {
        solver = new MergeIntervals();
    }

    @Test
    public void simple1() {
        int[][] result = solver.merge(new int[][] {{1, 3}, {2, 6}, {8, 10}, {15, 18}});
        assertNotNull(result);
        assertEquals(new int[][] {{1, 6}, {8, 10}, {15, 18}}, result);
    }

    @Test
    public void simple2() {
        int[][] result = solver.merge(new int[][] {{1, 4}, {4, 5}});
        assertNotNull(result);
        assertEquals(new int[][] {{1, 5}}, result);
    }
}
