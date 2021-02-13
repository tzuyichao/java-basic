package brute;

import java.util.Locale;

/**
 * = 125
 * Runtime: 38 ms, faster than 11.44% of Java online submissions for Valid Palindrome.
 * Memory Usage: 43.3 MB, less than 5.32% of Java online submissions for Valid Palindrome.
 */
public class ValidPalindrome {
    public boolean isPalindrome(String s) {
        String str = s.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
        System.out.println(str);
        String[] split = str.split("");
        if(split.length % 2 == 1) {
            for(int i=0; i<split.length/2; i++) {
                if(!split[i].equals(split[split.length-1-i])) {
                    return false;
                }
            }
        } else {
            for(int i=0; i<split.length/2; i++) {
                if(!split[i].equals(split[split.length-1-i])) {
                    return false;
                }
            }
        }
        return true;
    }
}
