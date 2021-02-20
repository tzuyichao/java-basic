package brute;

/**
 * = 98
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Validate Binary Search Tree.
 * Memory Usage: 38.6 MB, less than 69.79% of Java online submissions for Validate Binary Search Tree.
 */
public class ValidateBinarySearchTree {
    TreeNode pre = null;

    public boolean isValidBST(TreeNode root) {
        if(null == root) {
            return true;
        }
        if(!isValidBST(root.left)) {
            return false;
        }
        if(pre != null && root.val <= pre.val) {
            return false;
        }
        pre = root;
        return isValidBST(root.right);
    }
}
