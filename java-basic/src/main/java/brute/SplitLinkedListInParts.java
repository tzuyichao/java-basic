package brute;

/**
 * = 725
 */
public class SplitLinkedListInParts {
    public int length(ListNode listNode) {
        int count = 0;
        ListNode current = listNode;
        while(current != null) {
            count += 1;
            current = current.next;
        }
        return count;
    }

    public int[] groups(int length, int k) {
        int[] group = new int[k];
        int mod = length % k;
        for(int i=0; i<k; i++) {
            if(i < mod) {
                group[i] = length/k + 1;
            } else {
                group[i] = length/k;
            }
        }
        return group;
    }

    public int groupId(int idx, int[] group) {
        int sum = 0;
        for(int i=0; i<group.length; i++) {
            sum += group[i];
            if(sum > idx) {
                return i;
            }
        }
        return 0;
    }

    public ListNode[] splitListToParts(ListNode root, int k) {
        if(null == root) {
            return new ListNode[k];
        }
        int length = length(root);
        ListNode[] result = new ListNode[k];
        ListNode[] resultCurrent = new ListNode[k];
        ListNode current = root;
        int i = 0;
        int[] group = groups(length, k);
        while(current != null) {
            int groupId = groupId(i, group);
            if(result[groupId] == null) {
                result[groupId] = current;
                resultCurrent[groupId] = current;
            } else {
                resultCurrent[groupId].next = current;
                resultCurrent[groupId] = current;
            }
            i++;
            current = current.next;
        }
        for(i=0; i<resultCurrent.length; i++) {
            if(resultCurrent[i] != null) {
                resultCurrent[i].next = null;
            }
        }
        return result;
    }
}
