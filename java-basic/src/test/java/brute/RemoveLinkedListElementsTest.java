package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;

public class RemoveLinkedListElementsTest {
    RemoveLinkedListElements solver;

    @Before
    public void init() {
        solver = new RemoveLinkedListElements();
    }

    @Test
    public void simple1() {
        ListNode actual = solver.removeElements(ListNodeHelper.make(new int[] {1,2,6,3,4,5,6}), 6);
        ListNodeHelper.assertListNode(new int[] {1,2,3,4,5}, actual);
    }

    @Test
    public void simple2() {
        ListNode actual = solver.removeElements(ListNodeHelper.make(new int[] {1}), 1);
        assertNull(actual);
    }
}
