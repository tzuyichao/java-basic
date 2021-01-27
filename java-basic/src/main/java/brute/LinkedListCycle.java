package brute;

/**
 * = 141
 */
public class LinkedListCycle {
    int visited = -10000000;

    private boolean solve(ListNode node) {
        if(null == node || null == node.next) {
            return false;
        }
        if(node.val == visited) {
            return true;
        } else {
            node.val = visited;
            return solve(node.next);
        }
    }

    public boolean hasCycle(ListNode head) {
        return solve(head);
    }
}
