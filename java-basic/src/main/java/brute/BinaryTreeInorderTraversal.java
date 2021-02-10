package brute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * = 94
 */
public class BinaryTreeInorderTraversal {
    public void inorder(TreeNode current, Consumer<Integer> collector) {
        if(current != null) {
            inorder(current.left, collector);
            if(collector != null) {
                collector.accept(current.val);
            }
            inorder(current.right, collector);
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if(null == root) {
            return Collections.EMPTY_LIST;
        }
        List<Integer> inorder = new ArrayList<>();

        inorder(root, val -> {
            inorder.add(val);
        });
        return inorder;
    }
}
