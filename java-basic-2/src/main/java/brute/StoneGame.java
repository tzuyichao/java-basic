package brute;

/**
 * 877. Stone Game
 * 
 * Time Limit Exceeded
 */
public class StoneGame {
    public boolean stoneGame(int[] piles) {
        return helper(piles, 0, 0, 0, piles.length-1, 0);
    }

    private boolean helper(int[] piles, int curAlex, int curLee, int left, int right, int player) {
        if(left > right) {
            return curAlex > curLee;
        }
        if(player == 0) {
            return helper(piles, curAlex + piles[left], curLee, left+1, right, 1) ||
                    helper(piles, curAlex + piles[right], curLee, left, right-1, 1);
        } else {
            return helper(piles, curAlex, curLee + piles[left], left+1, right, 0) ||
                    helper(piles, curAlex, curLee + piles[right], left, right-1, 0);
        }
    }
}
