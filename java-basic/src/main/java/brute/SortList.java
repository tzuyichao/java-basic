package brute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortList {
    public ListNode sortList(ListNode head) {
        if(null == head || null == head.next) {
            return head;
        }
        List<ListNode> store = new ArrayList<>();
        ListNode current = head;
        while(current != null) {
            store.add(current);
            current = current.next;
        }
        Collections.sort(store, Comparator.comparingInt(a -> a.val));
        ListNode result = store.get(0);
        current = result;
        for(int i=1; i<store.size(); i++) {
            ListNode elem = store.get(i);
            current.next = elem;
            current = elem;
        }
        current.next = null;
        return result;
    }
}
