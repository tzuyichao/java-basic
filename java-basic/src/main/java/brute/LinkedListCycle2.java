package brute;

import java.util.HashSet;
import java.util.Set;

/**
 * = 142
 * v1:
 * Runtime: 3 ms, faster than 24.15% of Java online submissions for Linked List Cycle II.
 * Memory Usage: 40.4 MB, less than 21.11% of Java online submissions for Linked List Cycle II.
 * v2:
 * Runtime: 4 ms, faster than 15.54% of Java online submissions for Linked List Cycle II.
 * Memory Usage: 39.9 MB, less than 31.56% of Java online submissions for Linked List Cycle II.
 */
public class LinkedListCycle2 {
    public ListNode detectCycle(ListNode head) {
        if(null == head || null == head.next) {
            return null;
        }
        Set<ListNode> visitedStore = new HashSet<>();
        while(head != null) {
            if(visitedStore.contains(head)) {
                return head;
            } else {
                visitedStore.add(head);
            }
            head = head.next;
        }
        return null;
    }
}
