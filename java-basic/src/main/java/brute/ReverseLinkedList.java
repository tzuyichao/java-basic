package brute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * = 206
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
