package brute;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FindAllNumbersDisappearedInArrayTest {
    FindAllNumbersDisappearedInArray solver;

    @Before
    public void init() {
        solver = new FindAllNumbersDisappearedInArray();
    }

    private void assertExpect(int[] expects, List<Integer> actual) {
        assertEquals(expects.length, actual.size());
        for(int expect : expects) {
            assertTrue(actual.contains(expect));
        }
    }

    @Test
    public void simple1() {
        int[] nums = new int[] {4,3,2,7,8,2,3,1};
        int[] expects = new int[] {5, 6};
        List<Integer> actual = solver.findDisappearedNumbers(nums);
        assertExpect(expects, actual);
    }

    @Test
    public void simple2() {
        int[] nums = new int[] {4,3,2,7,8,2,3,2};
        int[] expects = new int[] {5, 6, 1};
        List<Integer> actual = solver.findDisappearedNumbers(nums);
        assertExpect(expects, actual);
    }

    @Test
    public void simple3() {
        int[] nums = new int[] {};
        int[] expects = new int[] {};
        List<Integer> actual = solver.findDisappearedNumbers(nums);
        assertExpect(expects, actual);
    }
}
