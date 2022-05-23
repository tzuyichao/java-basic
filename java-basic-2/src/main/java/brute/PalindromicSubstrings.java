package brute;

/**
 * 647. Palindromic Substrings
 * https://leetcode.com/problems/palindromic-substrings/
 *
 */
public class PalindromicSubstrings {
    public int countSubstrings(String s) {
        int res = 0;
        for(var i=0; i<s.length(); i++) {
            // abba
            res += count(s, i, i);
            // abcba
            res += count(s, i, i+1);
        }
        return res;
    }

    public int count(String s, int l, int r) {
        int res = 0;
        while(l >=0 && r <s.length() && s.charAt(l--) == s.charAt(r++)) {
            res++;
        }
        return res;
    }
}
