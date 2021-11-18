package brute;

/**
 * LeetCode: 32 Longest Valid Parentheses
 * Time Limit Exceeded
 *
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int result = 0;
        int left = 0;
        int right = 0;
        int l = s.length();
        for(int i=0; i<l; i++) {
            if(s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if(left == right) {
                result = Math.max(result, 2*right);
            } else if(right > left) {
                left = 0;
                right = 0;
            }
        }
        left = 0;
        right = 0;
        for(int i=l-1; i>=0; i--) {
            if(s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if(left == right) {
                result = Math.max(result, 2*left);
            } else if(left > right) {
                left = 0;
                right = 0;
            }
        }
        return result;
    }
}
