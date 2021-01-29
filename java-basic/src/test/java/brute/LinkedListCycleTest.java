package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LinkedListCycleTest {
    LinkedListCycle solver;

    @Before
    public void init() {
        solver = new LinkedListCycle();
    }

    @Test
    public void simple1() {
        ListNode head = new ListNode(3);
        ListNode listNode1 = new ListNode(2);
        head.next = listNode1;
        ListNode listNode2 = new ListNode(0);
        listNode1.next = listNode2;
        ListNode listNode3 = new ListNode(-4);
        listNode2.next = listNode3;
        listNode3.next = listNode1;

        assertTrue(solver.hasCycle(head));
    }

    @Test
    public void simple2() {
        ListNode head = new ListNode(3);
        ListNode listNode1 = new ListNode(2);
        head.next = listNode1;
        ListNode listNode2 = new ListNode(0);
        listNode1.next = listNode2;
        ListNode listNode3 = new ListNode(-4);
        listNode2.next = listNode3;

        assertFalse(solver.hasCycle(head));
    }

    @Test
    public void simple3() {
        ListNode head = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        head.next = listNode1;
        listNode1.next = head;

        assertTrue(solver.hasCycle(head));
    }

    @Test
    public void simple4() {
        ListNode head = new ListNode(1);

        assertFalse(solver.hasCycle(head));
    }

    @Test
    public void simple5() {
        assertFalse(solver.hasCycle(null));
    }
}
