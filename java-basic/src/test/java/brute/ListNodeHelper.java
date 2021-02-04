package brute;

public class ListNodeHelper {
    public static ListNode make(int[] data) {
        if(null == data || data.length == 0) {
            return null;
        }
        ListNode root = new ListNode(data[0]);
        ListNode current = root;
        for(int i=1; i<data.length; i++) {
            current.next = new ListNode(data[i]);
            current = current.next;
        }

        return root;
    }
}
