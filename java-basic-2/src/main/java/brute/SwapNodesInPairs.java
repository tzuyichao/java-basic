package brute;

/**
 * 24. Swap Nodes in Pairs
 * https://leetcode.com/problems/swap-nodes-in-pairs/
 *
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Swap Nodes in Pairs.
 * Memory Usage: 42 MB, less than 27.48% of Java online submissions for Swap Nodes in Pairs.
 */
public class SwapNodesInPairs {
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode swapPairs(ListNode head) {
        if(null == head) {
            return head;
        }
        ListNode current = head;
        ListNode prev = null;
        while(current != null) {
            if(current.next != null) {
                ListNode next = current.next;
                ListNode temp = next.next;

                next.next = current;
                current.next = temp;

                if(current == head) {
                    head = next;
                }
                if(prev != null) {
                    prev.next = next;
                }
                prev = current;
                current = temp;
            } else {
                break;
            }
        }
        return head;
    }
}
