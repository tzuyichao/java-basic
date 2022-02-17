package brute;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 49. Group Anagrams
 */
public class GroupAnagrams {
    public String profile(String str) {
        int[] d = new int[26];
        for(char c : str.toCharArray()) {
            int idx = c - 'a';
            d[idx] += 1;
        }
        StringBuilder sb = new StringBuilder();
        for(int v : d) {
            sb.append("_"+v);
        }
        return sb.toString();
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        //System.out.println(profile("bdddddddddd"));
        //System.out.println(profile("bbbbbbbbbbc"));
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
