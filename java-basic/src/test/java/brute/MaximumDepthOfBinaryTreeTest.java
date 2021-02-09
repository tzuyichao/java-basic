package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaximumDepthOfBinaryTreeTest {
    MaximumDepthOfBinaryTree solver;
    @Before
    public void init() {
        solver = new MaximumDepthOfBinaryTree();
    }

    @Test
    public void simple1() {
        int maxDepth = solver.maxDepth(TreeNodeHelper.make(new Integer[] {3,9,20,null,null,15,7}));
        assertEquals(3, maxDepth);
    }

    @Test
    public void simple2() {
        int maxDepth = solver.maxDepth(TreeNodeHelper.make(new Integer[] {1,null,2}));
        assertEquals(2, maxDepth);
    }

    @Test
    public void simple3() {
        int maxDepth = solver.maxDepth(TreeNodeHelper.make(new Integer[] {}));
        assertEquals(0, maxDepth);
    }

    @Test
    public void simple4() {
        int maxDepth = solver.maxDepth(TreeNodeHelper.make(new Integer[] {0}));
        assertEquals(1, maxDepth);
    }

    @Test
    public void simple5() {
        int maxDepth = solver.maxDepth(TreeNodeHelper.make(new Integer[] {1,2,3,4,null,null,5}));
        assertEquals(3, maxDepth);
    }
}
