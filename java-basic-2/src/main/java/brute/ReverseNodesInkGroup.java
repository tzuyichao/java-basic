package brute;

public class ReverseNodesInkGroup {
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if(k == 1) {
            return head;
        }
        if(null == head) {
            return head;
        }
        ListNode current = head;
        while(current != null) {
            if(current.next != null) {
                ListNode next = current.next;
                ListNode n2 = next.next;
                current.next = n2;
                next.next = current;
                if(current == head) {
                    head = next;
                }
                current = n2;
            } else {
                break;
            }
        }

        return head;
    }
}
