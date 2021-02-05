package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;

public class RemoveNthNodeFromEndOfListTest {
    RemoveNthNodeFromEndOfList solver;

    @Before
    public void init() {
        solver = new RemoveNthNodeFromEndOfList();
    }

    @Test
    public void simple1() {
        ListNode actual = solver.removeNthFromEnd(ListNodeHelper.make(new int[] {1,2,3,4,5}), 2);
        ListNodeHelper.assertListNode(new int[] {1,2,3,5}, actual);
    }

    @Test
    public void simple2() {
        ListNode actual = solver.removeNthFromEnd(ListNodeHelper.make(new int[] {1}), 1);
        assertNull(actual);
    }

    @Test
    public void simple3() {
        ListNode actual = solver.removeNthFromEnd(ListNodeHelper.make(new int[] {1,2}), 1);
        ListNodeHelper.assertListNode(new int[] {1}, actual);
    }
}
