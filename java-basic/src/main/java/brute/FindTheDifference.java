package brute;

import java.util.Arrays;

/**
 * = 389
 *
 */
public class FindTheDifference {
    public char[] toCharArray(String s) {
        char[] chars = new char[s.length()];
        for(int i=0; i<s.length(); i++) {
            chars[i] = s.charAt(i);
        }
        Arrays.sort(chars);
        return chars;
    }
    public char findTheDifference(String s, String t) {
        char[] chars = toCharArray(s);
        char[] chart = toCharArray(t);

        for(int i=0; i<chars.length; i++) {
            if(chars[i] != chart[i]) {
                return chart[i];
            }
        }
        return chart[chart.length-1];
    }
}
