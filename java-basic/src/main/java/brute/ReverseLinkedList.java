package brute;

/**
 * = 206
 * v1:
 * Runtime: 3 ms, faster than 6.40% of Java online submissions for Reverse Linked List.
 * Memory Usage: 40.1 MB, less than 5.72% of Java online submissions for Reverse Linked List.
 * v2:
 * Runtime: 1 ms, faster than 6.40% of Java online submissions for Reverse Linked List.
 * Memory Usage: 38.3 MB, less than 97.56% of Java online submissions for Reverse Linked List.
 * v3:
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse Linked List.
 * Memory Usage: 39 MB, less than 44.09% of Java online submissions for Reverse Linked List.
 */
public class ReverseLinkedList {
    public ListNode reverseList(ListNode head) {
        if(null == head) {
            return null;
        }
        ListNode result = null;
        while(head != null) {
            ListNode current = new ListNode(head.val);
            if(result != null) {
                current.next = result;
            }
            result = current;

            head = head.next;
        }
        return result;
    }
}
