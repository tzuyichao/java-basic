package brute;

/**
 * 2. Add Two Numbers
 * https://leetcode.com/problems/add-two-numbers/
 *
 * 第二次做這題
 * Runtime: 3 ms, faster than 73.11% of Java online submissions for Add Two Numbers.
 * Memory Usage: 47.7 MB, less than 51.61% of Java online submissions for Add Two Numbers.
 *
 * 03/16/2022 08:27	Accepted	3 ms	47.7 MB	java
 * 02/05/2021 16:03	Accepted	18 ms	40 MB	java
 */
public class AddTwoNumbers {
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public int len(ListNode l) {
        ListNode c = l;
        int counter = 0;
        while(c != null) {
            c = c.next;
            counter += 1;
        }
        return counter;
    }

    public ListNode carry(ListNode l) {
        ListNode c = l;
        boolean isCarry = false;
        int c_val = 0;
        while(c != null) {
            if(isCarry) {
                c.val += c_val;
            }
            if(c.val >= 10) {
                isCarry = true;
                c_val = c.val/10;
            } else {
                isCarry = false;
                c_val = 0;
            }
            c.val = c.val % 10;
            if(c.next == null && isCarry) {
                c.next = new ListNode(0);
            }
            c = c.next;
        }
        return l;
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode r1 = l1;
        ListNode r2 = l2;

        ListNode c1 = r1;
        ListNode c2 = r2;
        int len1 = len(r1);
        int len2 = len(r2);
        if(len1 >= len2) {
            while(c1 != null) {
                if(c2 != null) {
                    c1.val += c2.val;
                    c2 = c2.next;
                }
                c1 = c1.next;
            }
            return carry(r1);
        } else {
            while(c2 != null) {
                if(c1 != null) {
                    c2.val += c1.val;
                    c1 = c1.next;
                }
                c2 = c2.next;
            }
            return carry(r2);
        }
    }
}
