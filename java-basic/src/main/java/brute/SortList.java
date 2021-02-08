package brute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * = 148
 * v2:
 * Runtime: 9 ms, faster than 42.03% of Java online submissions for Sort List.
 * Memory Usage: 47.4 MB, less than 54.12% of Java online submissions for Sort List.
 */
public class SortList {
    public ListNode sortList(ListNode head) {
        if(null == head || null == head.next) {
            return head;
        }
        List<Integer> data = new ArrayList<>();
        ListNode current = head;
        while(current != null) {
            data.add(current.val);
            current = current.next;
        }
        Collections.sort(data);
        current = head;
        int i = 0;
        while(current != null) {
            current.val = data.get(i);
            i++;
            current = current.next;
        }
        return head;
    }
}
