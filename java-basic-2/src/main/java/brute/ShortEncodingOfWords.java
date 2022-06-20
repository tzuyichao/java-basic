package brute;

import java.util.Arrays;

/**
 * 820. Short Encoding of Words
 * https://leetcode.com/problems/short-encoding-of-words/
 */
public class ShortEncodingOfWords {
    public int minimumLengthEncoding(String[] words) {
        StringBuilder str = new StringBuilder();
        Arrays.sort(words, (a, b) -> {
            int l1 = a.length();
            int l2 = b.length();
            if(l1 > l2) {
                return -1;
            } else if(l1 < l2) {
                return 1;
            } else {
                return 0;
            }
        });
        for(String word: words) {
            System.out.println(word);
            int found = str.lastIndexOf(word);
            if(found == -1 || str.charAt(found + word.length()) != '#') {
                str.append(word).append('#');
            }
        }
        System.out.println(str);
        return str.length();
    }
}
