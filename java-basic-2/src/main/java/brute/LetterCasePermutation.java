package brute;

import java.util.ArrayList;
import java.util.List;

/**
 * 784. Letter Case Permutation
 * https://leetcode.com/problems/letter-case-permutation/
 *
 *
 */
public class LetterCasePermutation {
    public List<String> letterCasePermutation(String s) {
        List<String> res = new ArrayList();
        helper(s.toCharArray(), 0, res);
        return res;
    }

    public void helper(char[] s, int pos, List<String> res) {
        if(s.length == pos) {
            res.add(new String(s));
            return;
        }
        helper(s, pos+1, res);
        if(!Character.isLetter(s[pos])) return;
        s[pos] ^= 32;
        helper(s, pos+1, res);
        s[pos] ^= 32;
    }
}
