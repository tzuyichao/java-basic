package brute;

import java.util.HashSet;

/**
 * 804. Unique Morse Code Words
 *
 * https://leetcode.com/problems/unique-morse-code-words/
 */
public class UniqueMorseCodeWords {
    public int uniqueMorseRepresentations(String[] words) {
        String[] map = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        HashSet<String> store = new HashSet<>();
        for(String word: words) {
            StringBuilder sb = new StringBuilder();
            for(char c: word.toCharArray()) {
                sb.append(map[c-'a']);
            }
            store.add(sb.toString());
        }
        return store.size();
    }
}
