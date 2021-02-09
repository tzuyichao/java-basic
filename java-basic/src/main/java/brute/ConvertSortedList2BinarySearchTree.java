package brute;

import java.util.ArrayList;
import java.util.List;

/**
 * = 109
 */
public class ConvertSortedList2BinarySearchTree {
    public int treeDepth(TreeNode tree) {
        return 0;
    }

    public TreeNode makeTree(List<Integer> data, int rootIndex) {
        return null;
    }

    public TreeNode sortedListToBST(ListNode head) {
        if(null == head) {
            return null;
        }
        if(null == head.next) {
            TreeNode tree = new TreeNode();
            tree.val = head.val;
            return tree;
        }
        List<Integer> store = new ArrayList<>();
        while(head != null) {
            store.add(head.val);
            head = head.next;
        }
        return null;
    }
}
