package brute;

/**
 * 45. Jump Game II
 */
public class JumpGame2 {

    public int jump(int[] nums) {
        int l = nums.length;
        int c = 0;
        int step = 0;
        int i = 0;
        while (c < l - 1) {
            step++;
            int pre = c;
            for (; i <= pre; i++) {
                c = Math.max(c, i + nums[i]);
            }
            if (pre == c) {
                return -1;
            }
        }
        return step;
    }
}
