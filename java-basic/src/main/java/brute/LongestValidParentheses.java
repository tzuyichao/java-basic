package brute;

/**
 * LeetCode: 32 Longest Valid Parentheses
 * Time Limit Exceeded
 *
 */
public class LongestValidParentheses {
    public boolean isValid(String s) {
        int check = 0;
        for(char c: s.toCharArray()) {
            if(c == '(') {
                check++;
            } else {
                check--;
            }
            if(check < 0) {
                return false;
            }
        }
        return check == 0;
    }

    public int longestValidParentheses(String s) {
        int l = s.length();
        if(l < 2) {
            return 0;
        } else {
            int max = 0;
            for(int i=0; i<l-1; i++) {
                for(int j=l; j>i; j--) {
                    if(j-i > max) {
                        if(isValid(s.substring(i, j))) {
                            if(max < j-i) {
                                max = j-i;
                            }
                        }
                    }
                }
            }
            return max;
        }
    }
}
