package brute;

import org.junit.Before;
import org.junit.Test;

public class LevelTraversalTest {
    LevelTraversal solver;
    @Before
    public void init() {
        solver = new LevelTraversal();
    }

    @Test
    public void simple1() {
        TreeNode root = TreeNodeHelper.make(new Integer[] {5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1});
        solver.walk(root, (elem) -> {
            System.out.println(elem);
        });
    }

    @Test
    public void simple2() {
        TreeNode root = TreeNodeHelper.make(new Integer[] {5, 4, 8, 11, 13, 2, 1});
        solver.walk(root, (elem) -> {
            System.out.println(elem);
        });
    }
}
