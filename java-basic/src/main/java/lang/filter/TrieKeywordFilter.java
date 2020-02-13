package lang.filter;

import java.util.List;

public class TrieKeywordFilter implements NameFilter {
    private Node root = new Node();

    public TrieKeywordFilter(List<String> keys) {
        for(String key: keys) {
            addWord(key);
        }
    }

    public void addWord(String word) {
        Node temp = root;
        for(int i=0; i<word.length(); i++) {
            Character c = word.charAt(i);
            Node node = temp.getNextNode(c);
            if(null == node) {
                node = new Node();
                temp.addNextNode(c, node);
            }
            temp = node;
        }
    }

    @Override
    public String filter(String src, String mask) {
        StringBuilder result = new StringBuilder();
        Node temp = root;
        int begin = 0;
        int position = 0;
        while(position < src.length()) {
            Character c = src.charAt(position);
            temp = temp.getNextNode(c);
            if(null == temp) {
                result.append(src.charAt(begin));
                begin += 1;
                position = begin;
                temp = root;
                continue;
            } else if(temp.isLastCharacter()) {
                result.append(mask);
                position++;
                begin = position;
                temp = root;
            } else {
                position++;
            }
        }
        result.append(src.substring(begin));

        return result.toString();
    }
}
