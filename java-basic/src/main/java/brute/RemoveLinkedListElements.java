package brute;

/**
 * = 203
 */
public class RemoveLinkedListElements {
    public ListNode removeElements(ListNode head, int val) {
        if(null == head) {
            return null;
        }
        ListNode previous = null;
        ListNode current = head;
        while(current != null) {
            if(current.val == val) {
                if(previous == null) {
                    head = head.next;
                } else {
                    previous.next = current.next;
                }
            } else {
                previous = current;
            }
            current = current.next;
        }
        return head;
    }
}
