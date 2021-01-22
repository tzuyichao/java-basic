package brute;

/**
 * = 21
 */
public class MergeTwoSortedList {
    public boolean hasNext(ListNode listNode) {
        return listNode.next != null;
    }

    public ListNode last(ListNode listNode) {
        ListNode result = listNode;
        while(hasNext(result)) {
            result = result.next;
        }
        return result;
    }

    private void merge(ListNode l1, ListNode l2, ListNode result) {
        if(null == l1) {
            last(result).next = l2;
            return;
        }
        if(null == l2) {
            last(result).next = l1;
            return;
        }
        if(l1.val >= l2.val) {
            last(result).next = new ListNode(l2.val);
            merge(l1, l2.next, result);
        } else {
            last(result).next = new ListNode(l1.val);
            merge(l1.next, l2, result);
        }
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(null == l1) {
            return l2;
        }
        if(null == l2) {
            return l1;
        }
        ListNode result;
        if(l1.val > l2.val) {
            result = new ListNode(l2.val);
            merge(l1, l2.next, result);
        } else {
            result = new ListNode(l1.val);
            merge(l1.next, l2, result);
        }
        return result;
    }
}
