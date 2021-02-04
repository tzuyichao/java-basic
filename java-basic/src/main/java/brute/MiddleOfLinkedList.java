package brute;

public class MiddleOfLinkedList {
    public int length(ListNode listNode) {
        int count = 0;
        ListNode current = listNode;
        while(current != null) {
            count += 1;
            current = current.next;
        }
        return count;
    }

    public ListNode skip(ListNode listNode, int skip) {
        int count = 0;
        ListNode current = listNode;
        while(current != null) {
            if(count == skip) {
                return current;
            }
            count+=1;
            current = current.next;
        }
        return null;
    }

    public ListNode middleNode(ListNode head) {
        return skip(head, length(head)/2);
    }
}
