package circularlinkedlist;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
class Node {
    private int value;
    private Node nextNode;

    public Node(int value) {
        this.value = value;
    }
}
