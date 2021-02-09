package brute;

/**
 * = 108
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Convert Sorted Array to Binary Search Tree.
 * Memory Usage: 39 MB, less than 29.18% of Java online submissions for Convert Sorted Array to Binary Search Tree.
 */
public class ConvertSortedArray2BinarySearchTree {
    public TreeNode createTreeNode(int[] nums, int start, int end) {
        if(start > end) {
            return null;
        } else {
            int mid = (start + end)/2;
            TreeNode node = new TreeNode(nums[mid]);
            node.left = createTreeNode(nums, start, mid-1);
            node.right = createTreeNode(nums, mid+1, end);
            return node;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if(null == nums || nums.length == 0) {
            return null;
        }
        return createTreeNode(nums, 0, nums.length-1);
    }
}
