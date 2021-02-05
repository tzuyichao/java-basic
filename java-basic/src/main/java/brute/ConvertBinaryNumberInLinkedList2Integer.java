package brute;

/**
 * = 1290
 */
public class ConvertBinaryNumberInLinkedList2Integer {
    public int length(ListNode listNode) {
        int count = 0;
        ListNode current = listNode;
        while(current != null) {
            count += 1;
            current = current.next;
        }
        return count;
    }

    public int getDecimalValue(ListNode head) {
        if(head == null) {
            return 0;
        }
        int power = length(head)-1;
        int result = 0;
        ListNode current = head;
        while(current != null) {
            result += (current.val * Math.pow(2, power));
            power -= 1;
            current = current.next;
        }
        return result;
    }
}
