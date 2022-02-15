package brute;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DivideTwoIntegersTest {
    @Test
    public void testHappy1() {
        DivideTwoIntegers solver = new DivideTwoIntegers();
        assertThat(solver.divide(10, 3))
                .isEqualTo(3);
    }

    @Test
    public void testHappy2() {
        DivideTwoIntegers solver = new DivideTwoIntegers();
        assertThat(solver.divide(7, -3))
                .isEqualTo(-2);
    }

    @Test
    public void testHappy3() {
        DivideTwoIntegers solver = new DivideTwoIntegers();
        assertThat(solver.divide(-2147483648, -3))
                .isEqualTo(715827882);
    }

    @Test
    public void testHappy4() {
        DivideTwoIntegers solver = new DivideTwoIntegers();
        assertThat(solver.divide(-2147483648, -1))
                .isEqualTo(2147483647);
    }
}
