package heap;

public class Node {
    private int value;
    private Node right;
    private Node left;
    private Node parent;

    public Node(int value, Node parent) {
        this.value = value;
        this.parent = parent;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean hasFree() {
        if(right == null || left == null) {
            return true;
        } else {
            return false;
        }
    }

    public void removeChild(int value) {
        if(left != null && left.getValue() == value) {
            left = null;
        } else if(right != null && right.getValue() == value) {
            right = null;
        }

    }
}
