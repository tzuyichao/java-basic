package brute;

import org.junit.Before;
import org.junit.Test;

public class Tree2StrTest {
    Tree2Str solver;
    @Before
    public void init(){
        solver = new Tree2Str();
    }

    @Test
    public void simple1() {
        System.out.println(solver.tree2str(TreeNodeHelper.make(new Integer[] {1, 2, 3, 4})));
    }

    @Test
    public void simple2() {
        System.out.println(solver.tree2str(TreeNodeHelper.make(new Integer[] {1, 2, 3, null, 4})));
    }

    @Test
    public void simple3() {
        System.out.println(solver.tree2str(TreeNodeHelper.make(new Integer[] {})));
    }
}
