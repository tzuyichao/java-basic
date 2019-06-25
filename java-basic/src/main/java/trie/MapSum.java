package trie;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class MapSum {
    private Node root;

    private static class Node {
        public int value;
        public Map<Character, Node> next;

        public Node() {
            next = new TreeMap<>();
        }

        public Node(int value) {
            this();
            this.value = value;
        }
    }

    public MapSum() {
        this.root = new Node();
    }

    public void insert(String word, int value) {
        Node current = root;
        char[] chars = word.toCharArray();
        for(char ch: chars) {
            Node next = current.next.get(ch);
            if(next == null) {
                current.next.put(ch, new Node());
            }
            current = current.next.get(ch);
        }
        current.value = value;
    }

    public Optional<Integer> sum(String prefix) {
        Node current = root;
        char[] chars = prefix.toCharArray();
        for(char ch: chars) {
            Node next = current.next.get(ch);
            if(next == null) {
                return Optional.empty();
            }
            current = next;
        }
        return Optional.of(accumulateValue(current));
    }

    private int accumulateValue(Node node) {
        int result = node.value;
        for(char ch : node.next.keySet()) {
            result += accumulateValue(node.next.get(ch));
        }
        return result;
    }
}
