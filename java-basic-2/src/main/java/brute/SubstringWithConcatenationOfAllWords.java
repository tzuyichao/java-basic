package brute;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * 30. Substring with Concatenation of All Words
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/
 *
 * Runtime: 218 ms, faster than 45.75% of Java online submissions for Substring with Concatenation of All Words.
 * Memory Usage: 72.7 MB, less than 46.75% of Java online submissions for Substring with Concatenation of All Words.
 */
public class SubstringWithConcatenationOfAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        if(null == s || "".equals(s) || null == words || words.length == 0) {
            return Collections.EMPTY_LIST;
        }

        var res = new ArrayList<Integer>();
        var n = words.length;
        var len = words[0].length();
        var wordCount = new HashMap<String, Integer>();
        for(var word: words) {
            if(wordCount.containsKey(word)) {
                wordCount.compute(word, (k, v) -> ++v);
            } else {
                wordCount.put(word, 1);
            }
        }
        for(var i=0; i<=s.length()-n*len; ++i) {
            var strCount = new HashMap<String, Integer>();
            int j = 0;
            for(j = 0; j<n; ++j) {
                var idx = i+j*len;
                String t = s.substring(idx, idx+len);
                if(!wordCount.containsKey(t)) {
                    break;
                }
                if(strCount.containsKey(t)) {
                    strCount.compute(t, (k, v) -> ++v);
                } else {
                    strCount.put(t, 1);
                }
                if(strCount.get(t) > wordCount.get(t)) {
                    break;
                }
            }
            if(j == n) {
                res.add(i);
            }
        }

        return res;
    }
}
