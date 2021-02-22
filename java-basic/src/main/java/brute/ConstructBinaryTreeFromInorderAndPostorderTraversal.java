package brute;

/**
 * = 106
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    public TreeNode walk(int[] inorder, int[] postorder, int is, int ie, int ps, int pe) {
        if(ps > pe) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[pe]);
        for(int k=is; k<= ie; k++) {
            if(inorder[k] == postorder[pe]) {
                root.left = walk(inorder, postorder, is, k-1, ps, ps+(k-is)-1);
                root.right = walk(inorder, postorder, k+1, ie, ps+(k-is), pe-1);
            }
        }
        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return walk(inorder, postorder, 0, inorder.length-1, 0, postorder.length);
    }
}
