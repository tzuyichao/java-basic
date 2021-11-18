package brute;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

public class LongestValidParenthesesTest {
    @Test
    public void testHappy1() {
        LongestValidParentheses solution = new LongestValidParentheses();
        assertThat(solution.longestValidParentheses("(()"))
                .isEqualTo(2);
    }

    @Test
    public void testHappy2() {
        LongestValidParentheses solution = new LongestValidParentheses();
        assertThat(solution.longestValidParentheses(")()())"))
                .isEqualTo(4);
    }

    @Test
    public void testHappy3() {
        LongestValidParentheses solution = new LongestValidParentheses();
        assertThat(solution.longestValidParentheses(""))
                .isEqualTo(0);
    }

    @Test
    public void testHappy4() {
        LongestValidParentheses solution = new LongestValidParentheses();
        assertThat(solution.longestValidParentheses("))))((()(("))
                .isEqualTo(2);
    }

    @Test
    public void testHappy5() {
        LongestValidParentheses solution = new LongestValidParentheses();
        assertThat(solution.longestValidParentheses(")(())))(())())"))
                .isEqualTo(6);
    }

    // Time Limit Exceeded: PT0.2198947S
    // Dynamic Programming Version: PT0.0468736S
    @Test
    public void testHappy6() {
        LongestValidParentheses solution = new LongestValidParentheses();
        Instant start = Instant.now();
        assertThat(solution.longestValidParentheses("()()()(()))()()())))((()()()(()()))(()()()((()()))())(((())()())(()(()(()(())(((()())()))(()))()))))()())(()))))()()(())()()((())()()))))((()))))(()()((()(()(()())((())()()()()))()()()(())()()())((((()(())())))(((()(((()((((())())(()()()()(((((()))()(()(())))(((()()()()(()(((())(()(()()(()(()(())()))))))()))()())((()((((()(())(()()()(((((()())()))))())))((((()()()(()(())(((())(((()()((()((()(((()(()))(((())(((()((((()(())(((()((()(()())))))(()(()()(())))))()(()()((()))()))())())((())))()(())((((()((()))))()())()))((()(()())()())()()((()))())(()(()(())((((((()()((((())))())(((()()())))()(((()(()()((((())))))()()((((()(()()())(()(())()()()((()(()((((())))((((((())(()())()))))(()(()))()))))))(()()((()()()())(())))(((()))(()()()(()))((())()()()())()()(((())(()(())()()(()())((()()(((((())(()((((()(()()()(()))(()((((())()())()())())))())(((()(()((())()()((((()()()()))))))())())()(((((()())()(()()()()()(((())((((((()))(())()(()())(()(()())(()(())))())))(()()(()((((()()(())(()))()))(()))(()())())()))))))()()(())))))()))()(()())))((())(()()))()((()))()(())()()((((())()))((()(()))()(((()())()(()()((()())((((())()))))()(())())())())(((()(()())))((()))))()())))))(()((())))()())((()))()()))(())())()))()()((()(((())()()()((()()(()(())(()))())())(((()())(()())(()((()()()())()(()(((((((()()(((()(((((((())(()))))())()))))))))()(()(()((((())(()()(((()))()(())(((((((((()(()())())()(((()((()(((()(()()(()))(())))))))((("))
                .isEqualTo(590);
        Instant end = Instant.now();
        System.out.println(Duration.between(start, end));
    }
}
