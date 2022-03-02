package brute;

/**
 * 541. Reverse String II
 * https://leetcode.com/problems/reverse-string-ii/
 *
 */
public class ReverseString2 {
    public String reverseStr(String s, int k) {
        var l = s.length();
        if(l == 1) {
            return s;
        }
        StringBuilder res = new StringBuilder();

        int idx =0;
        int prev = idx;
        while(idx < l) {
            int d = idx/k;
            int m = idx%k;

            if(m == 0) {
                if(d % 2 == 1) {
                    StringBuilder part = new StringBuilder(s.substring(prev, idx));
                    res.append(part.reverse());
                } else {
                    res.append(s.substring(prev, idx));
                }
                prev = idx;
            }

            idx += 1;
        }

        return res.toString();
    }
}
