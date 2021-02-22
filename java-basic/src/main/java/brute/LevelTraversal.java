package brute;

import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

public class LevelTraversal {
    public void walk(TreeNode root, Consumer<Integer> payloadHandler) {
        if (null == root) {
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode n = queue.poll();
            if (payloadHandler != null) {
                payloadHandler.accept(n.val);
            }
            if (n.left != null) {
                queue.offer(n.left);
            }
            if (n.right != null) {
                queue.offer(n.right);
            }
        }
    }
}
