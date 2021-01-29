package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RemoveDuplicatesFromSortedArrayTest {
    RemoveDuplicatesFromSortedArray solver;

    @Before
    public void init() {
        solver = new RemoveDuplicatesFromSortedArray();
    }

    @Test
    public void simple1() {
        int[] nums = new int[] {1,1,2};
        int len = solver.removeDuplicates(nums);
        int expectLen = 2;
        int[] expectArrayContent = new int[] {1, 2};
        assertEquals(expectLen, len);
        for(int i=0; i<expectLen; i++) {
            assertEquals(expectArrayContent[i], nums[i]);
        }
    }

    @Test
    public void simple2() {
        int[] nums = new int[] {0,0,1,1,1,2,2,3,3,4};
        int len = solver.removeDuplicates(nums);
        int expectLen = 5;
        int[] expectArrayContent = new int[] {0,1,2,3,4};
        assertEquals(expectLen, len);
        for(int i=0; i<expectLen; i++) {
            assertEquals(expectArrayContent[i], nums[i]);
        }
    }

    @Test
    public void simple3() {
        int[] nums = new int[] {0,0};
        int len = solver.removeDuplicates(nums);
        int expectLen = 1;
        int[] expectArrayContent = new int[] {0};
        assertEquals(expectLen, len);
        for(int i=0; i<expectLen; i++) {
            assertEquals(expectArrayContent[i], nums[i]);
        }
    }

    @Test
    public void simple4() {
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
