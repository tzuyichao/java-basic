package brute;

/**
 * = 100
 */
public class SameTree {
    private boolean result = true;

    private void walkAndCompare(TreeNode p, TreeNode q) {
        if(p == null && q == null) {
            return;
        } else if((p == null && q != null) || (p != null && q == null)) {
            result = false;
            return;
        } else {
            if (p.val != q.val) {
                result = false;
                return;
            }
            walkAndCompare(p.right, q.right);
            walkAndCompare(p.left, q.left);
        }
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == q) {
            return true;
        }
        walkAndCompare(p, q);
        return result;
    }
}
