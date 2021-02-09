package brute;

import java.util.ArrayList;
import java.util.List;

/**
 * = 109
 * v1:
 * Runtime: 1 ms, faster than 44.27% of Java online submissions for Convert Sorted List to Binary Search Tree.
 * Memory Usage: 40.1 MB, less than 45.11% of Java online submissions for Convert Sorted List to Binary Search Tree.
 */
public class ConvertSortedList2BinarySearchTree {
    public TreeNode createTreeNode(List<Integer> nums, int start, int end) {
        if(start > end) {
            return null;
        } else {
            int mid = (start + end)/2;
            TreeNode node = new TreeNode(nums.get(mid));
            node.left = createTreeNode(nums, start, mid-1);
            node.right = createTreeNode(nums, mid+1, end);
            return node;
        }
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
        return createTreeNode(store, 0, store.size()-1);
    }
}
