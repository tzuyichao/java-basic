package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class ReverseLinkedListTest {
    ReverseLinkedList solver;

    @Before
    public void init() {
        solver = new ReverseLinkedList();
    }

    @Test
    public void simple1() {
        ListNode actual = solver.reverseList(ListNodeHelper.make(new int[] {1,2,3,4,5}));
        assertNotNull(actual);
        ListNodeHelper.assertListNode(new int[] {5,4,3,2,1}, actual);
    }
}
