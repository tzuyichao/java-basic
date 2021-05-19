package brute;

import java.util.LinkedList;
import java.util.Queue;

/**
 * = 173
 * Runtime: 17 ms, faster than 12.33% of Java online submissions for Binary Search Tree Iterator.
 * Memory Usage: 42.9 MB, less than 24.28% of Java online submissions for Binary Search Tree Iterator.
 */
public class BSTIterator {
    private Queue<Integer> store;
    private void preorder(TreeNode current, Queue<Integer> collector) {
        if(null == current) {
            return;
        }
        preorder(current.left, collector);
        collector.offer(current.val);
        preorder(current.right, collector);
    }

    public BSTIterator(TreeNode root) {
        store = new LinkedList<>();
        preorder(root, store);
    }

    public int next() {
        return store.poll();
    }

    public boolean hasNext() {
        return !store.isEmpty();
    }
}
