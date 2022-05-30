package brute;

/**
 * 318. Maximum Product of Word Lengths
 * https://leetcode.com/problems/maximum-product-of-word-lengths/
 * Time Limit Exceeded
 *
 * https://www.cnblogs.com/grandyang/p/5090058.html Solution 1
 */
public class MaximumProductOfWordLengths {
    public int maxProduct(String[] words) {
        int n = words.length;
        int[] mask = new int[n];
        int res = 0;
        for(int i=0; i<n; i++) {
            for(char c: words[i].toCharArray()) {
                mask[i] |= 1 << (c - 'a');
            }
            for(int j=0; j<i; j++) {
                if((mask[i] & mask[j]) == 0) {
                    res = Math.max(res, words[i].length() * words[j].length());
                }
            }
        }
        return res;
    }
}
