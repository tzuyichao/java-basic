package brute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * = 103
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Binary Tree Zigzag Level Order Traversal.
 * Memory Usage: 39.2 MB, less than 50.08% of Java online submissions for Binary Tree Zigzag Level Order Traversal.
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    List<List<Integer>> r = new ArrayList<>();

    public void walk(TreeNode n, int level) {
        if(n == null) {
            return;
        }
        if(r.size() == level - 1) {
            r.add(new ArrayList<>());
        }
        r.get(level-1).add(n.val);
        walk(n.left, level+1);
        walk(n.right, level+1);
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null) {
            return Collections.EMPTY_LIST;
        }
        walk(root, 1);

        for(int i=0; i<r.size(); i++) {
            if(i%2 == 1) {
                Collections.reverse(r.get(i));
            }
        }

        return r;
    }
}
