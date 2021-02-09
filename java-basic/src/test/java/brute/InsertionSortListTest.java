package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;

public class InsertionSortListTest {
    InsertionSortList solver;
    @Before
    public void init() {
        solver = new InsertionSortList();
    }

    @Test
    public void simple1() {
        ListNodeHelper.assertListNode(new int[] {1, 2, 3, 4}, solver.insertionSortList(ListNodeHelper.make(new int[] {4,2,1,3})));
    }

    @Test
    public void simple2() {
        assertNull(solver.insertionSortList(ListNodeHelper.make(new int[] {})));
    }

    @Test
    public void simple3() {
        ListNodeHelper.assertListNode(new int[] {1}, solver.insertionSortList(ListNodeHelper.make(new int[] {1})));
    }

    @Test
    public void simple4() {
        ListNodeHelper.assertListNode(new int[] {-1,0,3,4,5}, solver.insertionSortList(ListNodeHelper.make(new int[] {-1,5,3,4,0})));
    }
}
