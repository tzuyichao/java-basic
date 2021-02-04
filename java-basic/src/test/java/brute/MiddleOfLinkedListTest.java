package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MiddleOfLinkedListTest {
    MiddleOfLinkedList solver;

    @Before
    public void init() {
        solver = new MiddleOfLinkedList();
    }

    @Test
    public void len_simple1() {
        ListNode listNode = ListNodeHelper.make(new int[] {1,2,3,4,5});
        assertEquals(5, solver.length(listNode));
    }

    @Test
    public void len_simple2() {
        ListNode listNode = ListNodeHelper.make(new int[] {});
        assertEquals(0, solver.length(listNode));
    }

    @Test
    public void skip_simple1() {
        ListNode listNode = ListNodeHelper.make(new int[] {1,2,3,4,5});
        ListNodeHelper.assertListNode(new int[] {3, 4, 5}, solver.skip(listNode, 2));
    }

    @Test
    public void skip_simple2() {
        ListNode listNode = ListNodeHelper.make(new int[] {1,2,3,4,5});
        assertNull(solver.skip(listNode, 7));
    }

    @Test
    public void simple1() {
        ListNode listNode = ListNodeHelper.make(new int[] {1,2,3,4,5});
        ListNodeHelper.assertListNode(new int[] {3, 4, 5}, solver.middleNode(listNode));
    }

    @Test
    public void simple2() {
        ListNode listNode = ListNodeHelper.make(new int[] {1,2,3,4,5,6});
        ListNodeHelper.assertListNode(new int[] {4, 5, 6}, solver.middleNode(listNode));
    }
}
