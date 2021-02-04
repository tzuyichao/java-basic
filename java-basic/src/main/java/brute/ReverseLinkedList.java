package brute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * = 206
 * v1:
 * Runtime: 3 ms, faster than 6.40% of Java online submissions for Reverse Linked List.
 * Memory Usage: 40.1 MB, less than 5.72% of Java online submissions for Reverse Linked List.
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if(null == head) {
            return null;
        }
        List<ListNode> pool = new ArrayList<>();
        while(head != null) {
            pool.add(new ListNode(head.val));
            head = head.next;
        }
        Collections.reverse(pool);
        ListNode result = pool.get(0);
        ListNode current = result;
        for(int i=1; i<pool.size(); i++) {
            current.next = pool.get(i);
            current = current.next;
        }
        return result;
    }
}
