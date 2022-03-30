package brute;

import java.util.List;

/**
 * 211. Design Add and Search Words Data Structure
 * https://leetcode.com/problems/design-add-and-search-words-data-structure/
 *
 * helper function Ref: https://ttzztt.gitbooks.io/lc/content/
 * Runtime: 788 ms, faster than 32.91% of Java online submissions for Design Add and Search Words Data Structure.
 * Memory Usage: 256.1 MB, less than 30.42% of Java online submissions for Design Add and Search Words Data Structure.
 */
public class WordDictionary {
    class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean isWord;
    }

    TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode p = root;
        for(char c: word.toCharArray()) {
            int idx = c - 'a';
            if(p.child[idx] == null) {
                p.child[idx] = new TrieNode();
            }
            p = p.child[idx];
        }
        p.isWord = true;
    }

    public boolean search(String word) {
        return helper(word, 0, root);
    }

    private boolean helper(String word, int idx, TrieNode node) {
        if(idx == word.length()) {
            return node.isWord;
        }
        if(word.charAt(idx) != '.') {
            int c_index = word.charAt(idx)-'a';
            return (node.child[c_index] != null && helper(word, idx+1, node.child[c_index]));
        } else {
            for(int i=0; i<node.child.length; i++) {
                if(node.child[i] != null && helper(word, idx+1, node.child[i])) {
                    return true;
                }
            }
        }
        return false;
    }
}
