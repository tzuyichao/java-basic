package brute;

import java.util.ArrayList;
import java.util.List;

/**
 * = 206
 * v1:
 * Runtime: 3 ms, faster than 6.40% of Java online submissions for Reverse Linked List.
 * Memory Usage: 40.1 MB, less than 5.72% of Java online submissions for Reverse Linked List.
 * v2:
 * Runtime: 1 ms, faster than 6.40% of Java online submissions for Reverse Linked List.
 * Memory Usage: 38.3 MB, less than 97.56% of Java online submissions for Reverse Linked List.
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if(null == head) {
            return null;
        }
        List<ListNode> pool = new ArrayList<>();
        while(head != null) {
            ListNode previous = pool.size() == 0?null:pool.get(pool.size()-1);
            ListNode current = new ListNode(head.val);
            current.next = previous;
            pool.add(current);

            head = head.next;
        }
        return pool.size()>0?pool.get(pool.size()-1):null;
    }
}
