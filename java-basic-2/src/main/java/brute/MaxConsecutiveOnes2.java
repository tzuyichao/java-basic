package brute;

import java.util.LinkedList;
import java.util.Queue;

// https://www.cnblogs.com/cnoodle/p/14238833.html
public class MaxConsecutiveOnes2 {
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int k = 1;
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0, j = 0; j < nums.length; j++) {
            if(nums[j] == 0) {
                queue.offer(j);
            }
            if(queue.size() > k) {
                i = queue.poll() + 1;
            }
            max = Math.max(max, j-1+1);
        }
        return max;
    }
}