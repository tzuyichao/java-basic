package brute;

import java.util.Stack;

/**
 * 456. 132 Pattern
 * https://leetcode.com/problems/132-pattern/
 *
 */
public class Find123Pattern {
    public boolean find132pattern(int[] nums) {
        int third = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for(int i=nums.length-1; i>=0; i--) {
            if(nums[i] < third) {
                return true;
            }
            while(!stack.empty() && nums[i] > stack.peek()) {
                third = stack.pop();
            }
            stack.push(nums[i]);
        }
        return false;
    }
}
