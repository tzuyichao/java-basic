package brute;

import org.junit.Before;
import org.junit.Test;

import static brute.AssertHelper.verifyEquals;

public class NextPermutationTest {
    NextPermutation solver;

    @Before
    public void init() {
        solver = new NextPermutation();
    }

    @Test
    public void simple1() {
        int[] nums = new int[] {1, 2, 3};
        solver.nextPermutation(nums);
        verifyEquals(new int[] {1, 3, 2}, nums);
    }

    @Test
    public void simple2() {
        int[] nums = new int[] {3, 2, 1};
        solver.nextPermutation(nums);
        verifyEquals(new int[] {1, 2, 3}, nums);
    }

    @Test
    public void simple3() {
        int[] nums = new int[] {1, 1, 5};
        solver.nextPermutation(nums);
        verifyEquals(new int[] {1, 5, 1}, nums);
    }

    @Test
    public void simple4() {
        int[] nums = new int[] {1};
        solver.nextPermutation(nums);
        verifyEquals(new int[] {1}, nums);
    }
}
