package brute;

import java.util.HashMap;
import java.util.Map;

/**
 * = 290
 * v1:
 * Runtime: 2 ms, faster than 21.43% of Java online submissions for Word Pattern.
 * Memory Usage: 37.2 MB, less than 43.82% of Java online submissions for Word Pattern.
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String s) {
        String[] tokens = s.split(" ");
        String[] patterns = pattern.split("");
        if(tokens.length != patterns.length) {
            return false;
        }
        Map<String, String> checker = new HashMap<>();
        for(int i=0; i<tokens.length; i++) {
            if(checker.containsKey(patterns[i])) {
                if(!checker.get(patterns[i]).equals(tokens[i])) {
                    return false;
                }
            } else {
                if(checker.values().contains(tokens[i])) {
                    return false;
                } else {
                    checker.put(patterns[i], tokens[i]);
                }
            }
        }
        return true;
    }
}
