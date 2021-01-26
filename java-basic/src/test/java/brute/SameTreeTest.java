package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SameTreeTest {
    private SameTree sameTree;

    @Before
    public void init() {
        sameTree = new SameTree();
    }

    @Test
    public void simple1() {
        TreeNode p = null;
        TreeNode q = null;
        assertTrue(sameTree.isSameTree(p, q));
    }

    @Test
    public void simple2() {
        TreeNode p = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode q = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        assertTrue(sameTree.isSameTree(p, q));
    }

    @Test
    public void simple3() {
        TreeNode p = new TreeNode(1, null, new TreeNode(3));
        TreeNode q = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        assertFalse(sameTree.isSameTree(p, q));
    }

    @Test
    public void simple4() {
        TreeNode p = new TreeNode(1, new TreeNode(2, new TreeNode(4), null), new TreeNode(3));
        TreeNode q = new TreeNode(1, new TreeNode(2, new TreeNode(4), null), new TreeNode(3));
        assertTrue(sameTree.isSameTree(p, q));
    }
}
