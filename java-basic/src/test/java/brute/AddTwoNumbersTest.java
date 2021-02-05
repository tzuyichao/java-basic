package brute;

import org.junit.Before;
import org.junit.Test;

public class AddTwoNumbersTest {
    AddTwoNumbers solver;

    @Before
    public void init() {
        solver = new AddTwoNumbers();
    }

    @Test
    public void simple1() {
        ListNode actual = solver.addTwoNumbers(ListNodeHelper.make(new int[] {2, 4, 3}), ListNodeHelper.make(new int[] {5, 6, 4}));
        ListNodeHelper.assertListNode(new int[] {7, 0, 8}, actual);
    }

    @Test
    public void simple2() {
        ListNode actual = solver.addTwoNumbers(ListNodeHelper.make(new int[] {0}), ListNodeHelper.make(new int[] {0}));
        ListNodeHelper.assertListNode(new int[] {0}, actual);
    }

    @Test
    public void simple3() {
        ListNode actual = solver.addTwoNumbers(ListNodeHelper.make(new int[] {9,9,9,9,9,9,9}), ListNodeHelper.make(new int[] {9,9,9,9}));
        ListNodeHelper.assertListNode(new int[] {8,9,9,9,0,0,0,1}, actual);
    }
}
