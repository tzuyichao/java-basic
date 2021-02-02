package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TwoSum2InputArrayIsSortedTest {
    TwoSum2InputArrayIsSorted solver;
    @Before
    public void init() {
        solver = new TwoSum2InputArrayIsSorted();
    }

    @Test
    public void simple1() {
        int[] numbers = new int[] {2,7,11,15};
        int target = 9;
        int[] result = solver.twoSum(numbers, target);
        assertEquals(1, result[0]);
        assertEquals(2, result[1]);
    }
}
