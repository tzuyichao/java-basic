package brute;

/**
 * 318. Maximum Product of Word Lengths
 * https://leetcode.com/problems/maximum-product-of-word-lengths/
 * Time Limit Exceeded
 */
public class MaximumProductOfWordLengths {
    private boolean isTarget(String a, String b) {
        for(int c: a.chars().distinct().toArray()) {
            if(b.contains(String.valueOf((char) c))) {
                return false;
            }
        }
        return true;
    }

    public int maxProduct(String[] words) {
        int n = words.length;
        int res = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                int m = words[i].length() * words[j].length();
                if(i != j && m > res && isTarget(words[i], words[j])) {
                    res = m;
                }
            }
        }
        return res;
    }
}
