package brute;

public class SwappingNodesInLinkedList {
    public int length(ListNode head) {
        ListNode c = head;
        int count = 0;
        while(c != null) {
            count ++;
            c = c.next;
        }
        return count;
    }

    public ListNode swapNodes(ListNode head, int k) {
        if(null == head || null == head.next) {
            return head;
        }
        int len = length(head);
        if(len%2==1 && (len+1)/2 == k) {
            return head;
        }
        if(len == 2) {
            int temp = head.val;
            head.val = head.next.val;
            head.next.val = temp;
            return head;
        }
        if(k == 1 || len == k) {
            ListNode h = head;
            ListNode x = null;
            ListNode y = null;
            ListNode current = head;
            int idx = 0;
            while(current != null) {
                idx++;
                if(idx == len-1) {
                    x = current;
                }
                if(idx == len) {
                    y = current;
                }
                current = current.next;
            }
            y.next = h.next;
            h.next = null;
            x.next = h;
            return y;
        } else {
            ListNode g = null;
            ListNode h = null;
            ListNode x = null;
            ListNode y = null;
            ListNode current = head;
            int idx = 0;
            while(current != null) {
                idx++;
                if(idx == k-1) {
                    g = current;
                }
                if(idx == k) {
                    h = current;
                }
                if(idx == len+1-k) {
                    y = current;
                }
                if(idx == len-k) {
                    x = current;
                }
                current = current.next;
            }
            if(x == h) {
                ListNode yNext = y.next;
                y.next = h;
                h.next = yNext;
                g.next = y;
            } else if(g == y) {
                ListNode hNext = h.next;
                h.next = y;
                y.next = hNext;
                x.next = h;
            } else {
                ListNode hNext = h.next;
                ListNode yNext = y.next;
                g.next = y;
                x.next = h;
                y.next = hNext;
                h.next = yNext;
            }
        }
        return head;
    }
}
