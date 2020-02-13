package lang.filter;

import java.util.HashMap;
import java.util.Map;

public class Node {
    private Map<Character, Node> children = new HashMap<>();

    public void addNextNode(Character key, Node node) {
        children.put(key, node);
    }

    public Node getNextNode(Character key) {
        return children.get(key);
    }

    public boolean isLastCharacter() {
        return children.isEmpty();
    }
}
