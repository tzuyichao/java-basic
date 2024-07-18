package brute;

import java.util.Stack;

/**
 * 1379. Find a Corresponding Node of a Binary Tree in a Clone of That Tree
 * https://leetcode.com/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/
 *
 * Runtime: 22 ms, faster than 5.96% of Java online submissions for Find a Corresponding Node of a Binary Tree in a Clone of That Tree.
 * Memory Usage: 101.6 MB, less than 50.99% of Java online submissions for Find a Corresponding Node of a Binary Tree in a Clone of That Tree.
 */
public class FindACorrespondingNodeOfABinaryTreeInACloneOfThatTree {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        Stack<TreeNode> s = new Stack();
        s.push(cloned);
        while(!s.empty()) {
            TreeNode c = s.pop();
            if(c.val == target.val) {
                return c;
            }
            if(c.right != null) {
                s.push(c.right);
            }
            if(c.left != null) {
                s.push(c.left);
            }
        }
        return null;
    }
}
