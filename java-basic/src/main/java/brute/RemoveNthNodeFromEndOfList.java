package brute;

public class RemoveNthNodeFromEndOfList {
    public int length(ListNode listNode) {
        int count = 0;
        ListNode current = listNode;
        while(current != null) {
            count += 1;
            current = current.next;
        }
        return count;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int length = length(head);
        if(length == n) {
            return null;
        }

        return null;
    }
}
