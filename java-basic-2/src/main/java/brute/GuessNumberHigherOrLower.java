package brute;
/**
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is higher than the picked number
 *			      1 if num is lower than the picked number
 *               otherwise return 0
 * int guess(int num);
 */

/**
 * 374. Guess Number Higher or Lower
 * https://leetcode.com/problems/guess-number-higher-or-lower/
 *
 * Runtime: 0 ms, faster than 100.00% of Java online submissions for Guess Number Higher or Lower.
 * Memory Usage: 40.9 MB, less than 41.52% of Java online submissions for Guess Number Higher or Lower.
 */
class GuessGame {
    int guess(int num) {
        return 0;
    }
}
public class GuessNumberHigherOrLower extends GuessGame {
    public int guessNumber(int n) {
        var i = 1;
        var j = n;
        while(i <= j) {
            var mid = i + (j-i >> 1);
            var g = guess(mid);
            if(g == 0) {
                return mid;
            } else if(g == -1) {
                j = mid - 1;
            } else {
                i = mid + 1;
            }
        }
        return -1;
    }
}
