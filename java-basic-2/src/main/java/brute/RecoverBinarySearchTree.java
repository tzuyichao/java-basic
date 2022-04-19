package brute;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 99. Recover Binary Search Tree
 * https://leetcode.com/problems/recover-binary-search-tree/
 *
 * Runtime: 8 ms, faster than 7.69% of Java online submissions for Recover Binary Search Tree.
 * Memory Usage: 42.6 MB, less than 80.45% of Java online submissions for Recover Binary Search Tree.
 */
public class RecoverBinarySearchTree {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private void inorder(TreeNode root, List<TreeNode> nodeList, List<Integer> valList) {
        if (null == root) {
            return;
        }
        inorder(root.left, nodeList, valList);
        nodeList.add(root);
        valList.add(root.val);
        inorder(root.right, nodeList, valList);
    }

    public void recoverTree(TreeNode root) {
        List<TreeNode> nodeList = new ArrayList<>();
        List<Integer> valList = new ArrayList<>();
        inorder(root, nodeList, valList);
        valList = valList.stream().sorted().collect(Collectors.toList());
        for (int i = 0; i < nodeList.size(); i++) {
            nodeList.get(i).val = valList.get(i);
        }
    }
}
