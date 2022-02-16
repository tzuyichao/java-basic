package brute;

/**
 * 55. Jump Game
 *
 * Runtime: 3 ms, faster than 65.06% of Java online submissions for Jump Game.
 * Memory Usage: 68.1 MB, less than 15.99% of Java online submissions for Jump Game.
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        int l = nums.length;
        if(l == 0) {
            return false;
        }
        int max = 0;
        for(int i=0; i<l; i++) {
            if(i > max) {
                return false;
            }
            if(max >= l) {
                return true;
            }
            max = Math.max(max, nums[i] + i);
        }

        return true;
    }
}
