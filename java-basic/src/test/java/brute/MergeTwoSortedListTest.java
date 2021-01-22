package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MergeTwoSortedListTest {
    MergeTwoSortedList mergeTwoSortedList;

    @Before
    public void setup() {
        mergeTwoSortedList = new MergeTwoSortedList();
    }

    @Test
    public void test_happy() {
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(4)));
        ListNode l2 = new ListNode(1, new ListNode(3, new ListNode(4)));
        ListNode result = mergeTwoSortedList.mergeTwoLists(l1, l2);
        assertNotNull(result);
        StringBuffer r = new StringBuffer();
        while (result != null && mergeTwoSortedList.hasNext(result)) {
            r.append(result.val);
            result = result.next;
        }
        r.append(result.val);
        assertEquals("112344", r.toString());
    }

    @Test
    public void test_l1_empty() {
        ListNode l2 = new ListNode(45);
        ListNode result = mergeTwoSortedList.mergeTwoLists(null, l2);
        assertNotNull(result);
        assertEquals(45, result.val);
    }

    @Test
    public void test_l2_empty() {
        ListNode l1 = new ListNode(45);
        ListNode result = mergeTwoSortedList.mergeTwoLists(l1, null);
        assertNotNull(result);
        assertEquals(45, result.val);
    }

    @Test
    public void test_l1l2_empty() {
        ListNode result = mergeTwoSortedList.mergeTwoLists(null, null);
        assertNull(result);
    }
}
