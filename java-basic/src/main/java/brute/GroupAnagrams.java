package brute;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 49. Group Anagrams
 *
 * v2
 * Runtime: 53 ms, faster than 10.55% of Java online submissions for Group Anagrams.
 * Memory Usage: 73.9 MB, less than 5.03% of Java online submissions for Group Anagrams.
 *
 * v3
 * Runtime: 15 ms, faster than 46.98% of Java online submissions for Group Anagrams.
 * Memory Usage: 56.8 MB, less than 15.57% of Java online submissions for Group Anagrams.
 *
 * v4
 * Runtime: 9 ms, faster than 76.88% of Java online submissions for Group Anagrams.
 * Memory Usage: 55.8 MB, less than 24.41% of Java online submissions for Group Anagrams.
 */
public class GroupAnagrams {
    public String profile(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> d = new HashMap();

        for(String str: strs) {
            String p = profile(str);
            if(d.containsKey(p)) {
                d.get(p).add(str);
            } else {
                List<String> v = new ArrayList();
                v.add(str);
                d.put(p, v);
            }
        }

        return d.values().stream().collect(Collectors.toList());
    }
}
