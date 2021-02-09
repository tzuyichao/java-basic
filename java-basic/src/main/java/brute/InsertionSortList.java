package brute;

/**
 * = 147
 * v1:
 * Runtime: 30 ms, faster than 46.67% of Java online submissions for Insertion Sort List.
 * Memory Usage: 38.5 MB, less than 79.49% of Java online submissions for Insertion Sort List.
 */
public class InsertionSortList {
    public ListNode insert(ListNode head, ListNode subject) {
        ListNode current = head;
        ListNode previous = null;
        while(current != null) {
            if(current.val > subject.val) {
                if(previous == null) {
                    subject.next = current;
                    return subject;
                } else {
                    previous.next = subject;
                    subject.next = current;
                }
                return head;
            }
            previous = current;
            current = current.next;
        }
        previous.next = subject;
        subject.next = null;
        return head;
    }

    public ListNode insertionSortList(ListNode head) {
        if(null == head || null == head.next) {
            return head;
        }
        ListNode current = head.next;
        ListNode result = head;
        result.next = null;
        while(current != null) {
            ListNode temp = current;
            current = current.next;
            temp.next = null;
            result = insert(result, temp);
        }
        return result;
    }
}
