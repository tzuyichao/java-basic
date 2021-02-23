package brute;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper for 235
 */
public class FindPath {
    public List<TreeNode> path(TreeNode root, TreeNode p) {
        List<TreeNode> r = new ArrayList<>();
        r.add(root);
        while(root.val != p.val) {
            if(root.val > p.val) {
                root = root.left;
            } else {
                root = root.right;
            }
            r.add(root);
        }
        return r;
    }
}
