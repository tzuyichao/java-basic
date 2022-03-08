package brute;

import java.util.*;

/**
 * 187. Repeated DNA Sequences
 * https://leetcode.com/problems/repeated-dna-sequences/
 *
 * Runtime: 16 ms, faster than 93.59% of Java online submissions for Repeated DNA Sequences.
 * Memory Usage: 46.7 MB, less than 97.15% of Java online submissions for Repeated DNA Sequences.
 */
public class RepeatedDNASequences {
    public List<String> findRepeatedDnaSequences(String s) {
        int n = s.length();
        if(n <= 10) {
            return Collections.EMPTY_LIST;
        }
        List<String> res = new ArrayList<>();

        int mask = 0x7ffffff;
        int cur = 0;
        for(int i=0; i<9; i++) {
            cur = (cur << 3) | (s.charAt(i) & 7);
        }
        //System.out.println(cur);
        var map = new HashMap<Integer, Integer>();
        for(int i=9; i<n; i++) {
            cur = ((cur & mask) << 3) | (s.charAt(i) & 7);
            if(map.containsKey(cur)) {
                if(map.get(cur) == 1) {
                    res.add(s.substring(i-9, i+1));
                }
                map.put(cur, map.get(cur)+1);
            } else {
                map.put(cur, 1);
            }
        }
        //System.out.println(map);

        return res;
    }
}
