package brute;

import java.math.BigDecimal;

/**
 * = 2
 */
public class AddTwoNumbers {
    public ListNode BigDecimalToListNode(BigDecimal val) {
        String[] values = String.valueOf(val).split("");
        ListNode result = new ListNode(Integer.parseInt(values[values.length-1]));
        ListNode current = result;
        for(int i=values.length-2; i>=0; i--) {
            ListNode listNode = new ListNode(Integer.parseInt(values[i]));
            current.next = listNode;
            current = listNode;
        }
        return result;
    }

    public BigDecimal ListNodeToBigDecimal(ListNode head) {
        StringBuilder resultBuilder = new StringBuilder();
        ListNode current = head;
        while(current != null) {
            resultBuilder.append(current.val);
            current = current.next;
        }
        return new BigDecimal(resultBuilder.reverse().toString());
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigDecimal val1 = ListNodeToBigDecimal(l1);
        BigDecimal val2 = ListNodeToBigDecimal(l2);
        return BigDecimalToListNode(val1.add(val2));
    }
}
