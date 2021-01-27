package brute;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TreeNodeHelperTest {
    @Test
    public void simple() {
        Integer[] data = new Integer[] {5,4,8,11,null,13,4,7,2,null,null,null,1};
        TreeNode root = TreeNodeHelper.make(data);
        assertEquals(5, root.val);
        assertEquals(4, root.left.val);
        assertEquals(8, root.right.val);
        assertEquals(11, root.left.left.val);
        assertEquals(null, root.left.right);
        assertEquals(7, root.left.left.left.val);
        assertEquals(2, root.left.left.right.val);
    }
}
