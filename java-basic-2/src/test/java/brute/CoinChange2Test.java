package brute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class CoinChange2Test {
    @Test
    public void test_happy_1() {
        CoinChange2 solver = new CoinChange2();
        assertThat(solver.change(3, new int[]{2}), is(equalTo(0)));
    }

    @Test
    public void test_happy_2() {
        CoinChange2 solver = new CoinChange2();
        assertThat(solver.change(5, new int[]{1, 2, 5}), is(equalTo(4)));
    }
}
