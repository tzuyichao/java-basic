package brute;

import java.util.ArrayList;
import java.util.List;

/**
 * = 143
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        if(null == head) {
            return;
        }
        List<ListNode> store = new ArrayList<>();
        ListNode current = head;
        while(current != null) {
            store.add(current);
            current = current.next;
        }

        int n = store.size();
        for(int i=0; i<n; i++) {
            if(i<n/2) {
                store.get(i).next = store.get(n-1-i);
            }
            if(i==n/2) {
                store.get(i).next = null;
            }
            if(i>n/2) {
                store.get(i).next = store.get(n+1-i);
            }
        }
    }
}
