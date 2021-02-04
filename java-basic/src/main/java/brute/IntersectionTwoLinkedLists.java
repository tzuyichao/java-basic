package brute;

/**
 * = 160
 */
public class IntersectionTwoLinkedLists {

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) {
            return null;
        }
        ListNode headACurrent = headA;
        ListNode headBCurrent = headB;
        if(headACurrent == headBCurrent) {
            return headACurrent;
        }

        while(headACurrent != null) {
            while(headBCurrent != null) {
                if (headACurrent == headBCurrent) {
                    return headACurrent;
                }
                headBCurrent = headBCurrent.next;
            }
            headBCurrent = headB;
            headACurrent = headACurrent.next;
        }
        return null;
    }
}
