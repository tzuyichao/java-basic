package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ThirdMaximumNumberTest {
    ThirdMaximumNumber solver;

    @Before
    public void init() {
        solver = new ThirdMaximumNumber();
    }

    @Test
    public void simple1() {
        int[] nums = new int[] {3,2,1};
        int expect = 1;
        assertEquals(expect, solver.thirdMax(nums));
    }

    @Test
    public void simple2() {
        int[] nums = new int[] {1,2};
        int expect = 2;
        assertEquals(expect, solver.thirdMax(nums));
    }

    @Test
    public void simple3() {
        int[] nums = new int[] {2,2,3,1};
        int expect = 1;
        assertEquals(expect, solver.thirdMax(nums));
    }
}
