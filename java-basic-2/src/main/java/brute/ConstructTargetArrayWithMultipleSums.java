package brute;

import java.util.*;

/**
 * 1354. Construct Target Array With Multiple Sums
 * https://leetcode.com/problems/construct-target-array-with-multiple-sums/
 *
 * https://zxi.mytechroad.com/blog/simulation/eetcode-1354-construct-target-array-with-multiple-sums/
 * Time Limit Exceeded
 */
public class ConstructTargetArrayWithMultipleSums {
    public boolean isPossible(int[] target) {
        Arrays.sort(target);
        PriorityQueue<Integer> q = new PriorityQueue<>(target.length, Comparator.reverseOrder());
        int sum = Arrays.stream(target).sum();
        for(int i=target.length-1; i >=0; i--) {
            q.offer(target[i]);
        }
        //System.out.println(q);
        while(true) {
            int t = q.poll();
            sum -= t;
            if(t == 1 || sum == 1) return true;
            if(t < sum || sum == 0 || t%sum == 0) return false;
            t %= sum;
            sum += t;
            q.offer(t);
        }
    }
}
