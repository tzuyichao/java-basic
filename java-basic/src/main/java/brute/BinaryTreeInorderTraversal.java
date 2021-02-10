package brute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * = 94
 */
public class BinaryTreeInorderTraversal {
    public void inorder(TreeNode current, List<Integer> collector) {
        if(current != null) {
            inorder(current.left, collector);
            collector.add(current.val);
            inorder(current.right, collector);
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if(null == root) {
            return Collections.EMPTY_LIST;
        }
        List<Integer> inorder = new ArrayList<>();
        inorder(root, inorder);
        return inorder;
    }
}
