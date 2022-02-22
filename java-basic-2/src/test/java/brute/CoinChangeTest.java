package brute;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CoinChangeTest {
    @Test
    public void happy1() {
        CoinChange solver = new CoinChange();
        assertThat(solver.coinChange(new int[]{1, 2, 5}, 11), is(equalTo(3)));
    }

    @Test
    public void happy2() {
        CoinChange solver = new CoinChange();
        assertThat(solver.coinChange(new int[]{1}, 0), is(equalTo(0)));
    }

    @Test
    public void happy3() {
        CoinChange solver = new CoinChange();
        assertThat(solver.coinChange(new int[]{2}, 3), is(equalTo(-1)));
    }

    @Test
    public void happy4() {
        CoinChange solver = new CoinChange();
        assertThat(solver.coinChange(new int[] {186, 419, 83, 408}, 6249), is(equalTo(20)));
    }

    @Test
    @DisplayName("Time Limit Exceeded test case")
    public void happy5() {
        CoinChange solver = new CoinChange();
        assertThat(solver.coinChange(new int[] {411,412,413,414,415,416,417,418,419,420,421,422}, 9864),
                is(equalTo(24)));
    }
}
