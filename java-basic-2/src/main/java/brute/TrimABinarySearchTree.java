package brute;

/**
 * 669. Trim a Binary Search Tree
 * https://leetcode.com/problems/trim-a-binary-search-tree/
 *
 * Ref: https://www.cnblogs.com/grandyang/p/7583185.html
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Trim a Binary Search Tree.
 * Memory Usage: 41.8 MB, less than 90.59% of Java online submissions for Trim a Binary Search Tree.
 */
public class TrimABinarySearchTree {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(null == root) {
            return null;
        }
        if(root.val < low) {
            return trimBST(root.right, low, high);
        }
        if(root.val > high) {
            return trimBST(root.left, low, high);
        }
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);

        return root;
    }
}
