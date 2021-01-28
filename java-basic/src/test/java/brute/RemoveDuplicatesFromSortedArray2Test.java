package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RemoveDuplicatesFromSortedArray2Test {
    RemoveDuplicatesFromSortedArray2 solver;

    @Before
    public void init() {
        solver = new RemoveDuplicatesFromSortedArray2();
    }

    @Test
    public void simple1() {
        int[] nums = new int[] {1,1,1,2,2,3};
        int len = solver.removeDuplicates(nums);
        int expectLen = 5;
        int[] expectArrayContent = new int[] {1,1,2,2,3};
        assertEquals(expectLen, len);
        for(int i=0; i<expectLen; i++) {
            assertEquals(expectArrayContent[i], nums[i]);
        }
    }

    @Test
    public void simple2() {
        int[] nums = new int[] {0,0,1,1,1,1,2,3,3};
        int len = solver.removeDuplicates(nums);
        int expectLen = 7;
        int[] expectArrayContent = new int[] {0,0,1,1,2,3,3};
        assertEquals(expectLen, len);
        for(int i=0; i<expectLen; i++) {
            assertEquals(expectArrayContent[i], nums[i]);
        }
    }

    @Test
    public void simple3() {
        int[] nums = new int[] {};
        int len = solver.removeDuplicates(nums);
        int expectLen = 0;
        int[] expectArrayContent = new int[] {};
        assertEquals(expectLen, len);
        for(int i=0; i<expectLen; i++) {
            assertEquals(expectArrayContent[i], nums[i]);
        }
    }
}
