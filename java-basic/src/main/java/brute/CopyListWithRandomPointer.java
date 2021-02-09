package brute;

import java.util.ArrayList;
import java.util.List;

/**
 * = 138
 * Construct a *deep copy* of the list
 * v1:
 * Runtime: 3 ms, faster than 15.42% of Java online submissions for Copy List with Random Pointer.
 * Memory Usage: 38.6 MB, less than 77.82% of Java online submissions for Copy List with Random Pointer.
 */
public class CopyListWithRandomPointer {
    public Node copyRandomList(Node head) {
        if(null == head) {
            return head;
        }
        List<Node> store = new ArrayList<>();
        List<Node> newStore = new ArrayList<>();
        while(head != null) {
            newStore.add(new Node(head.val));
            store.add(head);
            head = head.next;
        }
        for(int i=0; i<newStore.size(); i++) {
            if(i != newStore.size()-1) {
                newStore.get(i).next = newStore.get(i + 1);
            }
            if(store.get(i).random != null) {
                newStore.get(i).random = newStore.get(store.indexOf(store.get(i).random));
            } else {
                newStore.get(i).random = null;
            }
        }

        return newStore.get(0);
    }
}
