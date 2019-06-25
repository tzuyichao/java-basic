package trie;

import java.util.Map;
import java.util.TreeMap;

public class Trie {
    private Node root;
    private int size;

    private static class Node {
        public boolean isWord;
        public Map<Character, Node> next;

        public Node() {
            next = new TreeMap<>();
        }

        public Node(boolean isWord) {
            this();
            this.isWord = isWord;
        }
    }

    public Trie() {
        this.root = new Node();
    }

    public void add(String word) {
        Node current = root;
        char[] chars = word.toCharArray();
        for(char ch : chars) {
            Node next = current.next.get(ch);
            if(next == null) {
                current.next.put(ch, new Node());
            }
            current = current.next.get(ch);
        }
        if(!current.isWord) {
            size++;
            current.isWord = true;
        }
    }

    public boolean contains(String word) {
        Node current = root;
        char[] chars = word.toCharArray();
        for(char ch : chars) {
            Node next = current.next.get(ch);
            if(next == null) {
                return false;
            }
            current = next;
        }
        if(current.isWord) {
            return true;
        }
        return false;
    }

    public void remove(String word) {
        Node current = root;
        char[] chars = word.toCharArray();
        for(char ch : chars) {
            Node next = current.next.get(ch);
            if(next == null) {
                return;
            }
            current = next;
        }
        current.isWord = false;
        size--;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
