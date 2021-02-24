package brute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * helper for 236
 */
public class FindPath236 {
    public boolean path(TreeNode node, List<TreeNode> store, TreeNode target) {
        if(node == null) {
            return false;
        }
        store.add(node);
        if(node.val == target.val) {
            return true;
        }
        if(path(node.left, store, target) || path(node.right, store, target)) {
            return true;
        }
        store.remove(store.size()-1);
        return false;
    }

    public List<TreeNode> path(TreeNode root, TreeNode p) {
        List<TreeNode> path = new ArrayList<>();
        if(path(root, path, p)) {
            System.out.println(path);
            return path;
        }
        return Collections.EMPTY_LIST;
    }
}
