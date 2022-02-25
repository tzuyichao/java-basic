package brute;

import java.util.List;
import java.util.stream.IntStream;

/**
 * 139. Word Break
 * https://leetcode.com/problems/word-break/
 *
 *
 */
public class WordBreak {
    public boolean wordBreak(String s, List<String> wordDict) {
        return false;
    }

    public int fib(int n) {
        int[] dp = new int[n+2];
        dp[0] = 0;
        dp[1] = 1;
        IntStream.range(2, n+1).forEach(item -> {
            dp[item] = dp[item-1] + dp[item-2];
        });
        return dp[n];
    }
}
