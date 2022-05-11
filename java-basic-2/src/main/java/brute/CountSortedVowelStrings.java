package brute;

import java.util.Map;

/**
 * 1641. Count Sorted Vowel Strings
 * https://leetcode.com/problems/count-sorted-vowel-strings/
 *
 * Ref: https://yuihuang.com/leetcode-1641-count-sorted-vowel-strings/
 */
public class CountSortedVowelStrings {
    Map<Character, Integer> vowels = Map.of('a', 1, 'e', 2, 'i', 3, 'o', 4, 'u', 5);

    public int countVowelStrings(int n) {
        int[][] dp = new int[55][5];
        for(var i=0; i<5; i++) {
            dp[0][i] = 1;
        }
        for(var i=1; i<=n; i++) {
            for(var j=0; j<5; j++) {
                if(j == 0) {
                    dp[i][j] = dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[n][4];
    }
}
