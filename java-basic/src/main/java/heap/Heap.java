package heap;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Heap {
    private Node root;

    public Heap() {}

    public void add(int value) {
        if(null == root) {
            root = new Node(value, null);
        } else {
            addValue(root, value);
        }
    }

    public int poll() {
        if(root == null) {
            throw new IllegalStateException("heap is empty");
        }
        int rootValue = root.getValue();

        if(root.getRight() == null && root.getLeft() == null) {
            root = null;
        } else {
            Node lastNode = last();
            if (lastNode.getParent() != null) {
                lastNode.getParent().removeChild(lastNode.getValue());
            }
            root.setValue(lastNode.getValue());
            verify(root);
            verifyRightHand(root.getRight());
        }

        return rootValue;
    }

    private void addValue(Node node, int value) {
        if(node.getRight() == null) {
            node.setRight(new Node(value, node));
            verify(node);
        } else if(node.getLeft() == null) {
            node.setLeft(new Node(value, node));
            verify(node);
        } else {
            if(node.getRight().hasFree()) {
                addValue(node.getRight(), value);
            } else {
                addValue(node.getLeft(), value);
            }
        }
    }

    private Node last() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        Node last = null;
        while(!queue.isEmpty()) {
            last = queue.poll();
            if(last.getRight() != null) {
                queue.offer(last.getRight());
            }
            if(last.getLeft() != null) {
                queue.offer(last.getLeft());
            }
        }
        return last;
    }

    private void verifyRightHand(Node node) {
        if(node == null) {
            return;
        }
        List<Node> candidate = new ArrayList<>();
        candidate.add(node);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()) {
            Node _node = queue.poll();
            if(_node.getRight() != null) {
                candidate.add(node.getRight());
                queue.offer(node.getRight());
            }
            if(_node.getLeft() != null) {
                candidate.add(node.getLeft());
                queue.offer(node.getLeft());
            }
        }
        candidate.stream().forEach(this::verify);
    }

    private void verify(Node node) {
        if(node == null) {
            return;
        }
        if(node.getLeft() != null && node.getLeft().getValue() < node.getValue()) {
            int leftValue = node.getLeft().getValue();
            node.getLeft().setValue(node.getValue());
            node.setValue(leftValue);
        }
        if(node.getRight() != null && node.getRight().getValue() < node.getValue()) {
            int rightValue = node.getRight().getValue();
            node.getRight().setValue(node.getValue());
            node.setValue(rightValue);
        }
    }

    public void print() {
        if(root == null) {
            System.out.println("Heap is empty");
        } else {
            Queue<Node> queue = new LinkedList<>();
            queue.offer(root);
            String space = " ";
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                System.out.print(space + "(" + node.getValue() + ", p:" + node.getParent() + ")");
                if (node.getRight() != null) {
                    queue.offer(node.getRight());
                }
                if (node.getLeft() != null) {
                    queue.offer(node.getLeft());
                }
            }
        }
    }
}
