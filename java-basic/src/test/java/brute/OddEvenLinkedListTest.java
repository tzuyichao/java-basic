package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;

public class OddEvenLinkedListTest {
    OddEvenLinkedList solver;
    @Before
    public void init() {
        solver = new OddEvenLinkedList();
    }

    @Test
    public void simple1() {
        ListNodeHelper.assertListNode(new int[] {1,3,5,2,4}, solver.oddEvenList(ListNodeHelper.make(new int[] {1,2,3,4,5})));
    }

    @Test
    public void simple2() {
        ListNodeHelper.assertListNode(new int[] {2,3,6,7,1,5,4}, solver.oddEvenList(ListNodeHelper.make(new int[] {2,1,3,5,6,4,7})));
    }

    @Test
    public void simple3() {
        assertNull(solver.oddEvenList(ListNodeHelper.make(new int[] {})));
    }

    @Test
    public void simple4() {
        ListNodeHelper.assertListNode(new int[] {1}, solver.oddEvenList(ListNodeHelper.make(new int[] {1})));
    }

    @Test
    public void simple5() {
        ListNodeHelper.assertListNode(new int[] {2,1}, solver.oddEvenList(ListNodeHelper.make(new int[] {2,1})));
    }
}
