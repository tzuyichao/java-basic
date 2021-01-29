package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class RemoveElementTest {
    RemoveElement removeElement;
    @Before
    public void init() {
        removeElement = new RemoveElement();
    }

    private void test_format1(int[] nums, int val, int expectLen) {
        int len = removeElement.removeElement(nums, val);
        assertEquals(expectLen, len);
        for(int i=0; i<len; i++) {
            assertNotEquals(val, nums[i]);
        }
    }

    @Test
    public void simple1() {
        int[] nums = new int[] {3, 2, 2, 3};
        int val = 3;
        test_format1(nums, val, 2);
    }

    @Test
    public void simple2() {
        int[] nums = new int[] {0,1,2,2,3,0,4,2};
        int val = 2;
        test_format1(nums, val, 5);
    }
}
