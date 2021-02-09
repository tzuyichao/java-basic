package brute;

import java.util.BitSet;

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
 */
public class LinkedListCycle2 {
    public ListNode detectCycle(ListNode head) {
        if(null == head || null == head.next) {
            return null;
        }
        BitSet visitedStore = new BitSet();
        while(head != null) {
            if(visitedStore.get(head.hashCode())) {
                return head;
            } else {
                visitedStore.set(head.hashCode());
            }
            head = head.next;
        }
        return null;
    }
}
