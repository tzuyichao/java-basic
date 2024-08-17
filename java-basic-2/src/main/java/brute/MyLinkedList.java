package brute;

public class MyLinkedList {
    static class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
        }
    }
    private Node head;

    public MyLinkedList() {
        head = null;
    }

    public int get(int index) {
        Node current = head;
        int i = 0;
        while (current != null) {
            if(i == index) {
                return current.data;
            }
            current = current.next;
            i += 1;
        }
        return -1;
    }

    public void addAtHead(int val) {
        if(head == null) {
            head = new Node(val);
        } else {
            Node newHead = new Node(val);
            newHead.next = head;
            head = newHead;
        }
    }

    public void addAtTail(int val) {
        if(head == null) {
            head = new Node(val);
            return;
        }
        Node current = head;
        while(current != null) {
            if(current.next == null) {
                current.next = new Node(val);
                return;
            }
            current = current.next;
        }
    }

    public void addAtIndex(int index, int val) {
        if (index == 0) {
            addAtHead(val);
            return;
        }
        Node prev = head;
        int i = 0;
        while (prev != null && i < index - 1) {
            prev = prev.next;
            i += 1;
        }
        if (prev == null) return;
        Node newNode = new Node(val);
        newNode.next = prev.next;
        prev.next = newNode;
    }

    public void deleteAtIndex(int index) {
        if (index == 0 && head != null) {
            head = head.next;
            return;
        }
        Node prev = head;
        int i = 0;
        while (prev != null && i < index - 1) {
            prev = prev.next;
            i += 1;
        }
        if (prev == null || prev.next == null) return;
        prev.next = prev.next.next;
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.addAtHead(1);
        list.addAtTail(3);
        list.addAtIndex(1, 4);
        list.get(1);
        list.deleteAtIndex(1);
        System.out.println(list.get(1));
    }
}
