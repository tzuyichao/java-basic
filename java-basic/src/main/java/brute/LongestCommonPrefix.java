package brute;

/**
 * = 14
 *
 * ["flower","flow","flight"]
 * ["dog","racecar","car"]
 * []
 *
 * v1:
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Longest Common Prefix.
 * Memory Usage: 37 MB, less than 73.09% of Java online submissions for Longest Common Prefix.
 *
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) {
            return "";
        }
        int shortestIdx = -1;
        int len = 300;
        for(int i=0; i<strs.length; i++) {
            if(strs[i].length() < len) {
                len = strs[i].length();
                shortestIdx = i;
            }
        }
        int commonIdx = len;
        while(commonIdx > 0) {
            String current = strs[shortestIdx].substring(0, commonIdx);
            System.out.println(current);
            boolean agree = true;
            for(int i=0; i<strs.length; i++) {
                if(i != shortestIdx) {
                    if(!strs[i].startsWith(current)) {
                        System.out.println(strs[i]);
                        agree = false;
                    }
                }
            }
            if(agree == true) {
                break;
            }
            commonIdx -= 1;
        }
        if(commonIdx == 0) {
            return "";
        } else {
            return strs[shortestIdx].substring(0, commonIdx);
        }
    }
}
