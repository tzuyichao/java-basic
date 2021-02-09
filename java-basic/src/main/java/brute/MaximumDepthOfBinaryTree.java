package brute;

import java.util.HashMap;
import java.util.Map;

/**
 * = 104
 * v1:
 * Runtime: 2 ms, faster than 5.14% of Java online submissions for Maximum Depth of Binary Tree.
 * Memory Usage: 39.2 MB, less than 29.68% of Java online submissions for Maximum Depth of Binary Tree.
 */
public class MaximumDepthOfBinaryTree {
    public void depth(TreeNode current, int depth, Map<TreeNode, Integer> leafDepth) {
        if(current.left == null && current.right == null) {
            leafDepth.put(current, depth);
            return;
        }
        if(current.left != null) {
            depth(current.left, depth+1, leafDepth);
        }
        if(current.right != null) {
            depth(current.right, depth+1, leafDepth);
        }
    }

    public int maxDepth(TreeNode root) {
        if(null == root) {
            return 0;
        }
        Map<TreeNode, Integer> leafDepth = new HashMap<>();
        TreeNode current = root;
        int depth = 1;
        if(current.left == null && current.right == null) {
            return depth;
        }
        if(current.left != null) {
            depth(current.left, depth+1, leafDepth);
        }
        if(current.right != null) {
            depth(current.right, depth+1, leafDepth);
        }
        return leafDepth.values().stream().max(Integer::compare).get();
    }
}
