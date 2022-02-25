package brute;

import java.util.stream.IntStream;

/**
 * 96. Unique Binary Search Trees
 * https://leetcode.com/problems/unique-binary-search-trees/
 *
 * Runtime: 1 ms, faster than 15.22% of Java online submissions for Unique Binary Search Trees.
 * Memory Usage: 40.4 MB, less than 31.81% of Java online submissions for Unique Binary Search Trees.
 * 
 */
public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        var dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        IntStream.range(2, n+1).forEach(item -> {
            var sum = 0;
            for(int i=1; i<item+1; i++) {
                sum += (dp[i-1] * dp[item-i]);
            }
            dp[item] = sum;
        });
        return dp[n];
    }
}
