package brute;

import java.util.HashMap;
import java.util.Map;

/**
 * = 142
 * v1:
 * Runtime: 3 ms, faster than 24.15% of Java online submissions for Linked List Cycle II.
 * Memory Usage: 40.4 MB, less than 21.11% of Java online submissions for Linked List Cycle II.
 */
public class LinkedListCycle2 {
    public ListNode detectCycle(ListNode head) {
        if(null == head || null == head.next) {
            return null;
        }
        Map<ListNode, Boolean> store = new HashMap();
        while(head != null) {
            if(store.containsKey(head)) {
                return head;
            } else {
                store.put(head, Boolean.TRUE);
            }
            head = head.next;
        }
        return null;
    }
}
