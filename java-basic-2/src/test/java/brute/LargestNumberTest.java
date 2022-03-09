package brute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class LargestNumberTest {
    @Test
    public void happy1() {
        var solver = new LargestNumber();
        assertThat(solver.largestNumber(new int[] {10,2}), is(equalTo("210")));
    }

    @Test
    public void happy2() {
        var solver = new LargestNumber();
        assertThat(solver.largestNumber(new int[] {3,30,34,5,9}), is(equalTo("9534330")));
    }

    @Test
    public void happy3() {
        var solver = new LargestNumber();
        assertThat(solver.largestNumber(new int[] {0, 0}), is(equalTo("0")));
    }
}
