package brute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ValidPalindromeIITest {
    @Test
    public void happy1() {
        var solver = new ValidPalindromeII();
        assertThat(solver.validPalindrome("axbcbaba"), is(equalTo(false)));
    }
}
