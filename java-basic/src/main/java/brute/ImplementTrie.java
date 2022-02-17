package brute;

/**
 * 208. Implement Trie (Prefix Tree)
 *
 * Runtime: 51 ms, faster than 65.15% of Java online submissions for Implement Trie (Prefix Tree).
 * Memory Usage: 68 MB, less than 25.32% of Java online submissions for Implement Trie (Prefix Tree).
 */
public class ImplementTrie {
    class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean isWord;
    }

    class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode p = root;
            for(char c : word.toCharArray()) {
                int idx = c - 'a';
                if(p.child[idx] == null) {
                    p.child[idx] = new TrieNode();
                }
                p = p.child[idx];
            }
            p.isWord = true;
        }

        public boolean search(String word) {
            TrieNode p = root;
            for(char c: word.toCharArray()) {
                int idx = c - 'a';
                if(p.child[idx] == null) {
                    return false;
                }
                p = p.child[idx];
            }
            return p.isWord;
        }

        public boolean startsWith(String prefix) {
            TrieNode p = root;
            for(char c: prefix.toCharArray()) {
                int idx = c - 'a';
                if(p.child[idx] == null) {
                    return false;
                }
                p = p.child[idx];
            }
            return true;
        }
    }
}
