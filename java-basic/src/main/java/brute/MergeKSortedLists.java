package brute;

/**
 * = 23
 * v1:
 * Runtime: 262 ms, faster than 6.37% of Java online submissions for Merge k Sorted Lists.
 * Memory Usage: 40.8 MB, less than 50.96% of Java online submissions for Merge k Sorted Lists.
 */
public class MergeKSortedLists {
    private boolean allDone(ListNode[] indexes) {
        boolean result = false;
        for(ListNode listNode: indexes) {
            if(listNode != null) {
                return true;
            }
        }
        return result;
    }

    private ListNode findSmall(ListNode[] indexes) {
        ListNode target = null;
        int idx=0;
        for(int i=0; i<indexes.length; i++) {
            if(indexes[i] != null) {
                if(target != null) {
                    if(indexes[i].val < target.val) {
                        idx = i;
                        target = indexes[i];
                    }
                } else {
                    idx = i;
                    target = indexes[i];
                }
            }
        }
        indexes[idx] = indexes[idx].next;
        return target;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if(null == lists) {
            return null;
        }
        boolean allEmpty = true;
        for(ListNode listNode: lists) {
            if(listNode != null) {
                allEmpty = false;
            }
        }
        if(allEmpty) {
            return null;
        }
        ListNode[] indexes = new ListNode[lists.length];
        for(int i=0; i<indexes.length; i++) {
            indexes[i] = lists[i];
        }
        ListNode result = null;
        ListNode rCurrent = null;
        while(allDone(indexes)) {
            ListNode smallest = findSmall(indexes);
            if(result == null) {
                result = smallest;
                rCurrent = smallest;
            } else {
                rCurrent.next = smallest;
                rCurrent = smallest;
            }
        }
        return result;
    }
}
