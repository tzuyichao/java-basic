package collection;

import org.junit.Test;

public class FenwickTreeTest {
    @Test
    public void testBuild() {
        FenwickTree tree = new FenwickTree(new long[]{0, 3, 1, 5, 8, 12});
        System.out.println(tree);
    }
}
