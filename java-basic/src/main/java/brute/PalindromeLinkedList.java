package brute;

import java.util.Stack;

/**
 * = 234
 * v1:
 * Runtime: 2 ms, faster than 41.52% of Java online submissions for Palindrome Linked List.
 * Memory Usage: 42.8 MB, less than 45.37% of Java online submissions for Palindrome Linked List.
 */
public class PalindromeLinkedList {
    public int length(ListNode listNode) {
        int count = 0;
        ListNode current = listNode;
        while(current != null) {
            count += 1;
            current = current.next;
        }
        return count;
    }

    public boolean isPalindrome(ListNode head) {
        if(null == head) {
            return true;
        }
        int length = length(head);
        int count = 0;
        Stack<Integer> stack = new Stack<>();
        while(head != null) {
            if(length % 2 == 0) {
                if(count < length/2) {
                    stack.push(head.val);
                } else {
                    if(head.val != stack.pop()) {
                        return false;
                    }
                }
            } else {
                if(count < length/2) {
                    stack.push(head.val);
                } else if (count > length/2){
                    if(head.val != stack.pop()) {
                        return false;
                    }
                }
            }
            count += 1;
            head = head.next;
        }
        if(stack.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
