package brute;

/**
 * = 112
 */
public class PathSum {
    private boolean has = false;
    private void left(TreeNode node, TreeNode parent, TreeNode parentSumNode, int targetSum) {
        if(node != null) {
            TreeNode leftSumNode = new TreeNode(parentSumNode.val + node.val);
            parentSumNode.left = leftSumNode;
            left(node.left, node, leftSumNode, targetSum);
            right(node.right, node, leftSumNode, targetSum);
        } else {
            if(parentSumNode.val == targetSum && parent.right == null && parent.left == null) {
                has = true;
            }
            return;
        }
    }

    private void right(TreeNode node, TreeNode parent, TreeNode parentSumNode, int targetSum) {
        if(node != null) {
            TreeNode rightSumNode = new TreeNode(parentSumNode.val + node.val);
            parentSumNode.right = rightSumNode;
            left(node.left, node, rightSumNode, targetSum);
            right(node.right, node, rightSumNode, targetSum);
        } else {
            if(parentSumNode.val == targetSum && parent.right == null && parent.left == null) {
                has = true;
            }
            return;
        }
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) {
            return has;
        }
        TreeNode sumTree = new TreeNode(root.val);
        left(root.left, root, sumTree, targetSum);
        right(root.right, root, sumTree, targetSum);
        return has;
    }
}
