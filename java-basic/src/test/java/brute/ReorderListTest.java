package brute;

import org.junit.Before;
import org.junit.Test;

public class ReorderListTest {
    ReorderList solver;
    @Before
    public void init() {
        solver = new ReorderList();
    }

    @Test
    public void simple1() {
        solver.reorderList(ListNodeHelper.make(new int[] {1, 2, 3, 4}));
    }
}
