package brute;

import java.util.ArrayList;
import java.util.List;

/**
 * = 3
 * Runtime: 412 ms, faster than 5.02% of Java online submissions for Longest Substring Without Repeating Characters.
 * Memory Usage: 39.4 MB, less than 32.50% of Java online submissions for Longest Substring Without Repeating Characters.
 */
public class LongestSubstringWithoutRepeatingCharacters {
    public boolean isRepeat(String s, int start, int len, char check) {
        for(int i=start; i<start+len; i++) {
            if(s.charAt(i) == check) {
                return true;
            }
        }
        return false;
    }

    public int lengthOfLongestSubstring(String s) {
        if("".equals(s)) {
            return 0;
        }
        List<Integer> collect = new ArrayList<>();
        int len = s.length();
        for(int i=0; i<len; i++) {
            int currIdx = i;
            int curr = 1;
            for(int j=currIdx+1; j<len; j++) {
                if (isRepeat(s, currIdx, curr, s.charAt(j))) {
                    break;
                } else {
                    curr += 1;
                }
            }
            collect.add(curr);
        }

        return collect.stream().max(Integer::compareTo).get();
    }
}
