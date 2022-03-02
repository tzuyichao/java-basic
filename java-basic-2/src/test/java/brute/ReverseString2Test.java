package brute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ReverseString2Test {
    @Test
    public void happy1() {
        var solver = new ReverseString2();
        assertThat(solver.reverseStr("abcdefghij", 3), is(equalTo("cbadefihgj")));
    }

    @Test
    public void happy2() {
        var solver = new ReverseString2();
        assertThat(solver.reverseStr("abcdefgh", 3), is(equalTo("cbadefhg")));
    }

    @Test
    public void happy3() {
        var solver = new ReverseString2();
        assertThat(solver.reverseStr("ab", 3), is(equalTo("ba")));
    }
}
