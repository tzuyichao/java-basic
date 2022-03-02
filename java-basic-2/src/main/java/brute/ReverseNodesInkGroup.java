package brute;

/**
 * 25. Reverse Nodes in k-Group
 * https://leetcode.com/problems/reverse-nodes-in-k-group/
 *
 * I cannot solve this puzzle now. Reference: https://www.programcreek.com/2014/05/leetcode-reverse-nodes-in-k-group-java/
 * Runtime: 1 ms, faster than 59.91% of Java online submissions for Reverse Nodes in k-Group.
 * Memory Usage: 46.1 MB, less than 15.53% of Java online submissions for Reverse Nodes in k-Group.
 *
 */
public class ReverseNodesInkGroup {
    static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if(k == 1 || null == head) {
            return head;
        }
        ListNode fakeRoot = new ListNode();
        fakeRoot.next = head;
        ListNode prev = fakeRoot;
        int i = 0;
        ListNode c = head;
        while(c != null) {
            i++;
            if(i%k == 0) {
                prev = reverse(prev, c.next);
                c = prev.next;
            } else {
                c = c.next;
            }
        }
        return fakeRoot.next;
    }

    public ListNode reverse(ListNode prev, ListNode next) {
        ListNode last = prev.next;
        ListNode curr = last.next;

        while(curr != next) {
            last.next = curr.next;
            curr.next = prev.next;
            prev.next = curr;
            curr = last.next;
        }

        return last;
    }
}
