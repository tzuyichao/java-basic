package brute;

/**
 * 45. Jump Game II
 */
public class JumpGame2 {

    public int jump(int[] nums) {
        int l = nums.length;
        int c = 0;
        int step = 0;
        int last = 0;
        for(int i=0; i<l-1; i++) {
            c = Math.max(c, i+nums[i]);
            if(i == last) {
                last = c;
                step++;
                if(c >= l-1) {
                    break;
                }
            }
        }
        return step;
    }
}
