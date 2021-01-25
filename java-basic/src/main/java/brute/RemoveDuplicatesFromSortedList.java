package brute;

/**
 * = 83
 */
public class RemoveDuplicatesFromSortedList {
    public ListNode deleteDuplicates(ListNode head) {
        if(null == head) {
            return null;
        }
        int current = head.val;
        ListNode result = new ListNode(head.val);
        ListNode currentResultNode = result;
        ListNode previousNode = head;
        while(previousNode.next != null) {
            if(current != previousNode.next.val) {
                currentResultNode.next = new ListNode(previousNode.next.val);
                currentResultNode = currentResultNode.next;
            }
            previousNode = previousNode.next;
            current = previousNode.val;
        }
        return result;
    }
}
