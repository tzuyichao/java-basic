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
 * v3:
 * Memory Limit Exceeded
 * v4:
 * Runtime: 5 ms, faster than 8.56% of Java online submissions for Linked List Cycle II.
 * Memory Usage: 39.6 MB, less than 36.03% of Java online submissions for Linked List Cycle II.
 */
public class LinkedListCycle2 {
    public ListNode detectCycle(ListNode head) {
        if(null == head || null == head.next) {
            return null;
        }
        Set<Integer> store = new HashSet<>();
        while(head != null) {
            if(store.contains(head.hashCode())) {
                return head;
            } else {
                store.add(head.hashCode());
            }
            head = head.next;
        }
        return null;
    }
}
