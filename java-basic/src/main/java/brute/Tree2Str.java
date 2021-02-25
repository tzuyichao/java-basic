package brute;

/**
 * = 606
 */
public class Tree2Str {
    public String walkLeft(TreeNode l, boolean siblingExist) {
        if(l == null && siblingExist) {
            return "()";
        } else if(l == null && !siblingExist) {
            return "";
        }
        return "(" + l.val + (l.right!=null?walkLeft(l.left, true):walkLeft(l.left, false)) + walkRight(l.right) +")";
    }

    public String walkRight(TreeNode r) {
        if(r == null) {
            return "";
        }
        return "(" + r.val + (r.right!=null?walkLeft(r.left, true):walkLeft(r.left, false)) + walkRight(r.right) + ")";
    }

    public String tree2str(TreeNode t) {
        if(t == null) {
            return "";
        }
        return t.val + (t.right!=null?walkLeft(t.left, true):walkLeft(t.left, false)) + walkRight(t.right);
    }
}
