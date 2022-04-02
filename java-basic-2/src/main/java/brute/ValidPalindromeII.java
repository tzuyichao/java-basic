package brute;

/**
 * 680. Valid Palindrome II
 * https://leetcode.com/problems/valid-palindrome-ii/
 *
 * Runtime: 9 ms, faster than 75.34% of Java online submissions for Valid Palindrome II.
 * Memory Usage: 54.5 MB, less than 48.65% of Java online submissions for Valid Palindrome II.
 */
public class ValidPalindromeII {
    public boolean checkDeep(String s, int low, int high) {
        while(low < high) {
            if(s.charAt(low) != s.charAt(high)) {
                return false;
            }
            low += 1;
            high -= 1;
        }
        return true;
    }

    public boolean validPalindrome(String s) {
        int len = s.length();
        if(len <= 2) {
            return true;
        }
        int low = 0;
        int high = len-1;
        while(low < high) {
            if(s.charAt(low) != s.charAt(high)) {
                if(checkDeep(s, low, high-1) || checkDeep(s, low+1, high)) {
                    return true;
                } else {
                    return false;
                }
            }
            low += 1;
            high -= 1;
        }
        return true;
    }
}
