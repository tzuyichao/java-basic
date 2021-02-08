package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class SplitLinkedListInPartsTest {
    SplitLinkedListInParts solver;
    @Before
    public void init() {
        solver = new SplitLinkedListInParts();
    }

    @Test
    public void test_groupId() {
        int length = 7;
        int k = 3;
        int[] group = solver.groups(length, k);
        for(int i=0; i<length; i++) {
            System.out.println(solver.groupId(i, group));
        }
    }

    @Test
    public void simple1() {
        assertTrue(ListNodeHelper.isEqualsTo(new int[][] {{1}, {2}, {3}, null, null}, solver.splitListToParts(ListNodeHelper.make(new int[] {1, 2, 3}), 5)));
    }

    @Test
    public void simple2() {
        assertTrue(ListNodeHelper.isEqualsTo(new int[][] {{1,2,3}, {4,5}, {6,7}}, solver.splitListToParts(ListNodeHelper.make(new int[] {1, 2, 3, 4, 5, 6, 7}), 3)));
    }

    @Test
    public void simple3() {
        assertTrue(ListNodeHelper.isEqualsTo(new int[][] {null, null, null}, solver.splitListToParts(ListNodeHelper.make(new int[] {}), 3)));
    }

    @Test
    public void simple4() {
        assertTrue(ListNodeHelper.isEqualsTo(new int[][] {null}, solver.splitListToParts(ListNodeHelper.make(new int[] {}), 1)));
    }
}
