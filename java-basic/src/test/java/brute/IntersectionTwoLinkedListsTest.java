package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class IntersectionTwoLinkedListsTest {
    IntersectionTwoLinkedLists solver;
    @Before
    public void init() {
        solver = new IntersectionTwoLinkedLists();
    }

    @Test
    public void simple1() {
        // @@"
        ListNode tail = ListNodeHelper.make(new int[] {8,4,5});
        ListNode headA = ListNodeHelper.make(new int[] {4,1}, tail);
        ListNode headB = ListNodeHelper.make(new int[] {5,6,1}, tail);
        ListNode actual = solver.getIntersectionNode(headA, headB);
        assertNotNull(actual);
        int[] expect = new int[] {8, 4, 5};
        ListNodeHelper.assertListNode(expect, actual);
        // TODO: skip test like leetcode
    }
}
