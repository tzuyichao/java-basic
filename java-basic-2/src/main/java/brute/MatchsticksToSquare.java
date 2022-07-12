package brute;

import java.util.Arrays;

/**
 * 473. Matchsticks to Square
 * https://leetcode.com/problems/matchsticks-to-square/
 *
 * from: https://dev.to/seanpgallivan/solution-matchsticks-to-square-2fk8
 */
public class MatchsticksToSquare {
    class Solution {
        int side;
        public boolean makesquare(int[] matchsticks) {
            Arrays.sort(matchsticks);
            int total = 0;
            for(int stick: matchsticks) {
                total += stick;
            }
            side = total/4;
            if((float)total/4 > side || matchsticks[matchsticks.length-1] > side) return false;
            return btrack(matchsticks.length-1, side, 0, matchsticks);
        }

        public boolean btrack(int i, int space, int done, int[] matchstcks) {
            if(done == 3) return true;
            if(i == -1) return false;
            int num = matchstcks[i];
            if(num > space)
                return btrack(i-1, space, done, matchstcks);
            matchstcks[i] = side+1;
            if(num == space)
                return btrack(matchstcks.length-2, side, done+1, matchstcks);
            else {
                if(btrack(i-1, space-num, done, matchstcks)) return true;
                matchstcks[i] = num;
                return btrack(i-1, space, done, matchstcks);
            }
        }
    }
}
