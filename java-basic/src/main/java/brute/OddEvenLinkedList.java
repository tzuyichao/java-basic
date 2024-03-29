package brute;

import java.util.LinkedList;
import java.util.Queue;

/**
 * = 328
 * v1.
 * Runtime: 2 ms, faster than 9.29% of Java online submissions for Odd Even Linked List.
 * Memory Usage: 38.8 MB, less than 40.65% of Java online submissions for Odd Even Linked List.
 */
public class OddEvenLinkedList {
    public int length(ListNode head) {
        ListNode c = head;
        int count = 0;
        while(c != null) {
            count ++;
            c = c.next;
        }
        return count;
    }

    public ListNode oddEvenList(ListNode head) {
        if(null == head || null == head.next) {
            return head;
        }
        int length = length(head);
        if(length == 2) {
            return head;
        }
        Queue<Integer> odd = new LinkedList<>();
        Queue<Integer> even = new LinkedList<>();
        int idx = 1;
        ListNode current = head;
        while(current != null) {
            if(idx % 2 == 1) {
                odd.offer(current.val);
            } else {
                even.offer(current.val);
            }
            idx += 1;
            current = current.next;
        }

        current = head;
        while(current != null) {
            if(!odd.isEmpty()) {
                current.val = odd.poll();
            } else if(odd.isEmpty() && !even.isEmpty()) {
                current.val = even.poll();
            }
            current = current.next;
        }

        return head;
    }
}
