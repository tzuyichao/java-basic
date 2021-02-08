package brute;

import org.junit.Before;
import org.junit.Test;

public class MergeKSortedListsTest {
    MergeKSortedLists solver;
    @Before
    public void init() {
        solver = new MergeKSortedLists();
    }

    @Test
    public void simple1() {
        ListNode[] lists = new ListNode[] {
          ListNodeHelper.make(new int[] {1,4,5}),
          ListNodeHelper.make(new int[] {1,3,4}),
          ListNodeHelper.make(new int[] {2,6}),
        };
        ListNodeHelper.assertListNode(new int[] {1,1,2,3,4,4,5,6}, solver.mergeKLists(lists));
    }
}
