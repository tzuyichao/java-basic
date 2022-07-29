package brute;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 890. Find and Replace Pattern
 * https://leetcode.com/problems/find-and-replace-pattern/
 */
public class FindAndReplacePattern {
    public List<String> findAndReplacePattern(String[] words, String pattern) {
        return Stream.of(words).filter(word -> {
            Map<Character, Character> map = new HashMap<>();
            for(int i=0; i<pattern.length(); i++) {
                char c1 = pattern.charAt(i);
                char c2 = word.charAt(i);
                if(map.containsKey(c1)) {
                    if(map.get(c1) != c2) {
                        return false;
                    }
                } else {
                    if(map.values().contains(c2)) {
                        return false;
                    }
                    map.put(c1, c2);
                }
            }
            return true;
        }).collect(Collectors.toList());
    }
}
