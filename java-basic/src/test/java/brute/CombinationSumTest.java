package brute;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@Ignore
public class CombinationSumTest {
    CombinationSum solver;

    @Before
    public void init() {
        solver = new CombinationSum();
    }

    @Test
    public void simple1() {
        int[] nums = new int[] {2,3,6,7};
        int target = 7;

        List<List<Integer>> result = solver.combinationSum(nums, target);
        assertNotNull(result);
        System.out.println(result);
        assertEquals(2, result.size());
    }

    @Test
    public void simple2() {
        int[] nums = new int[] {2,3,6,7};
        int target = 8;

        List<List<Integer>> result = solver.combinationSum(nums, target);
        assertNotNull(result);
        System.out.println(result);
        assertEquals(3, result.size());
    }
}
