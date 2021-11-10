package string;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ParenthesesTest {
    @Test
    public void test_validation_happy() {
        boolean result = Parentheses.isValid("()()()");
        assertThat(result)
                .isTrue();
    }

    @Test
    public void test_validation_wrong_start() {
        boolean result = Parentheses.isValid(")()()");
        assertThat(result)
                .isFalse();
    }

    @Test
    public void test_validation_mismatch() {
        boolean result = Parentheses.isValid("(()()");
        assertThat(result)
                .isFalse();
    }

    @Test
    public void test_generate() {
        System.out.println(Parentheses.generate(6));
    }
}
