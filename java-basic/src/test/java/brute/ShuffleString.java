package brute;

import java.nio.CharBuffer;

/**
 * = 1528
 * java.nio.CharBuffer version
 * Runtime: 1 ms, faster than 99.91% of Java online submissions for Shuffle String.
 * Memory Usage: 38.9 MB, less than 78.15% of Java online submissions for Shuffle String.
 */
public class ShuffleString {
    public String restoreString(String s, int[] indices) {
        int len = s.length();
        CharBuffer cb = CharBuffer.allocate(len);
        for(int i=0; i<len; i++) {
            cb.put(indices[i], s.charAt(i));
        }
        return cb.toString();
    }
}
