package brute;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 518. Coin Change 2
 * https://leetcode.com/problems/coin-change-2/
 *
 * Runtime: 20 ms, faster than 28.07% of Java online submissions for Coin Change 2.
 * Memory Usage: 42.5 MB, less than 51.12% of Java online submissions for Coin Change 2.
 */
public class CoinChange2 {
    public int change(int amount, int[] coins) {
        var dp = new int[amount+1];
        dp[0] = 1;
        for(var coin: coins) {
            IntStream.range(1, amount+1).forEach(money -> {
                if(money - coin >= 0) {
                    dp[money] += dp[money - coin];
                }
            });
        }
        return dp[amount];
    }
}
