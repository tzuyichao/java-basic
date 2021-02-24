package brute;

import org.junit.Before;
import org.junit.Test;

public class FindPath236Test {
    FindPath236 solver ;
    @Before
    public void init() {
        solver = new FindPath236();
    }

    @Test
    public void simple1() {
        TreeNode root = TreeNodeHelper.make(new Integer[] {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4});
        System.out.println(solver.path(root, new TreeNode(8)));
    }
}
