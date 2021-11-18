package brute;

import org.junit.Test;

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
}
