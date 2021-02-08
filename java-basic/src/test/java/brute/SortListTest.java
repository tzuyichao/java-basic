package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;

public class SortListTest {
    SortList solver;
    @Before
    public void init() {
        solver = new SortList();
    }

    @Test
    public void simple1() {
        assertNull(solver.sortList(ListNodeHelper.make(new int[] {})));
    }

    @Test
    public void simple2() {
        ListNodeHelper.assertListNode(new int[] {1}, solver.sortList(ListNodeHelper.make(new int[] {1})));
    }

    @Test
    public void simple3() {
        ListNodeHelper.assertListNode(new int[] {-1,0,3,4,5}, solver.sortList(ListNodeHelper.make(new int[] {-1,5,3,4,0})));
    }

    @Test
    public void simple4() {
        ListNodeHelper.assertListNode(new int[] {1,2,3,4}, solver.sortList(ListNodeHelper.make(new int[] {4,2,1,3})));
    }
}
