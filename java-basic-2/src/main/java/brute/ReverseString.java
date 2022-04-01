package brute;

/**
 * 344. Reverse String
 * https://leetcode.com/problems/reverse-string/
 *
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Reverse String.
 * Memory Usage: 48.9 MB, less than 94.07% of Java online submissions for Reverse String.
 */
public class ReverseString {
    public void reverseString(char[] s) {
        var n = s.length;
        for(int i=0; i<n/2; i++) {
            char tmp = s[i];
            s[i] = s[n-1-i];
            s[n-1-i] = tmp;
        }
    }
}
