package brute;

import java.util.HashMap;
import java.util.Map;

/**
 * = 141
 */
public class LinkedListCycle {
    Map<ListNode, Boolean> check = new HashMap();

    private boolean solve(ListNode node) {
        if(null == node || null == node.next) {
            return false;
        }
        check.put(node, true);
        if(check.containsKey(node.next)) {
            return true;
        } else {
            return solve(node.next);
        }
    }

    public boolean hasCycle(ListNode head) {
        check.put(head, true);
        return solve(head);
    }
}
