package brute;

/**
 * = 237
 * 他沒說不能改val...老實說這題覺得有點冏
 */
public class DeleteNodeInLinkedList {
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
