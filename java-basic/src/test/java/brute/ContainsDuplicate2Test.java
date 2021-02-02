package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ContainsDuplicate2Test {
    ContainsDuplicate2 solver;

    @Before
    public void init() {
        solver = new ContainsDuplicate2();
    }

    @Test
    public void simple1() {
        int[] nums = new int[] {1,2,3,1};
        int k = 3;
        assertTrue(solver.containsNearbyDuplicate(nums, k));
    }

    @Test
    public void simple2() {
        int[] nums = new int[] {1,0,1,1};
        int k = 1;
        assertTrue(solver.containsNearbyDuplicate(nums, k));
    }

    @Test
    public void simple3() {
        int[] nums = new int[] {1,2,3,1,2,3};
        int k = 2;
        assertFalse(solver.containsNearbyDuplicate(nums, k));
    }

    @Test
    public void simple5() {
        int[] nums = new int[] {1,0,1,1};
        int k = 6;
        assertTrue(solver.containsNearbyDuplicate(nums, k));
    }

    @Test
    public void simple6() {
        int[] nums = new int[] {1};
        int k = 6;
        assertFalse(solver.containsNearbyDuplicate(nums, k));
    }

    @Test
    public void simple7() {
        int[] nums = new int[] {1};
        int k = 0;
        assertFalse(solver.containsNearbyDuplicate(nums, k));
    }

    @Test
    public void simple8() {
        int[] nums = new int[] {1};
        int k = 1;
        assertFalse(solver.containsNearbyDuplicate(nums, k));
    }

    @Test
    public void simple9() {
        int[] nums = new int[] {};
        int k = 1;
        assertFalse(solver.containsNearbyDuplicate(nums, k));
    }
}
