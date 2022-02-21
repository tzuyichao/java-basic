package brute;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CoinChangeTest {
    @Test
    public void happy1() {
        CoinChange solver = new CoinChange();
        assertThat(solver.coinChange(new int[]{1, 2, 5}, 11))
                .isEqualTo(3);
    }

    @Test
    public void happy2() {
        CoinChange solver = new CoinChange();
        assertThat(solver.coinChange(new int[]{1}, 0))
                .isEqualTo(0);
    }

    @Test
    public void happy3() {
        CoinChange solver = new CoinChange();
        assertThat(solver.coinChange(new int[]{2}, 3))
                .isEqualTo(-1);
    }

    @Test
    public void happy4() {
        CoinChange solver = new CoinChange();
        assertThat(solver.coinChange(new int[] {186, 419, 83, 408}, 6249))
                .isEqualTo(20);
    }

    @Test
    public void happy5() {
        CoinChange solver = new CoinChange();
        assertThat(solver.coinChange(new int[] {411,412,413,414,415,416,417,418,419,420,421,422}, 9864))
                .isEqualTo(20);
    }
}
