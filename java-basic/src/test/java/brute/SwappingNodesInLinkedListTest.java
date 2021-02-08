package brute;

import org.junit.Before;
import org.junit.Test;

public class SwappingNodesInLinkedListTest {
    SwappingNodesInLinkedList solver;
    @Before
    public void init() {
        solver = new SwappingNodesInLinkedList();
    }

    @Test
    public void simple1() {
        ListNodeHelper.assertListNode(new int[] {1,4,3,2,5}, solver.swapNodes(ListNodeHelper.make(new int[] {1,2,3,4,5}), 2));
    }

    @Test
    public void simple2() {
        ListNodeHelper.assertListNode(new int[] {7,9,6,6,8,7,3,0,9,5}, solver.swapNodes(ListNodeHelper.make(new int[] {7,9,6,6,7,8,3,0,9,5}), 5));
    }

    @Test
    public void simple3() {
        ListNodeHelper.assertListNode(new int[] {1}, solver.swapNodes(ListNodeHelper.make(new int[] {1}), 1));
    }

    @Test
    public void simple4() {
        ListNodeHelper.assertListNode(new int[] {2,1}, solver.swapNodes(ListNodeHelper.make(new int[] {1,2}), 1));
    }

    @Test
    public void simple5() {
        ListNodeHelper.assertListNode(new int[] {1,2,3}, solver.swapNodes(ListNodeHelper.make(new int[] {1,2,3}), 2));
    }

    @Test
    public void simple7() {
        ListNodeHelper.assertListNode(new int[] {1,2,3,4,5}, solver.swapNodes(ListNodeHelper.make(new int[] {1,2,3,4,5}), 3));
    }

    @Test
    public void simple6() {
        ListNodeHelper.assertListNode(new int[] {5,2,3,4,1}, solver.swapNodes(ListNodeHelper.make(new int[] {1,2,3,4,5}), 5));
    }

    @Test
    public void simple8() {
        ListNodeHelper.assertListNode(new int[] {55,60,78,53,93,37,31,4,61,13,11,51,34,83,24,96,5,77,1,67}, solver.swapNodes(ListNodeHelper.make(new int[] {55,60,78,53,93,37,31,4,61,11,13,51,34,83,24,96,5,77,1,67}), 11));
    }
}
