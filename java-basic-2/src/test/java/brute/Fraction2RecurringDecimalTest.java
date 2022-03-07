package brute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class Fraction2RecurringDecimalTest {
    @Test
    public void happy1() {
        var solver = new Fraction2RecurringDecimal();
        assertThat(solver.fractionToDecimal(1, 2), is(equalTo("0.5")));
    }

    @Test
    public void happy2() {
        var solver = new Fraction2RecurringDecimal();
        assertThat(solver.fractionToDecimal(2, 1), is(equalTo("2")));
    }

    @Test
    public void happy3() {
        var solver = new Fraction2RecurringDecimal();
        assertThat(solver.fractionToDecimal(4, 333), is(equalTo("0.(012)")));
    }

    @Test
    public void happy4() {
        var solver = new Fraction2RecurringDecimal();
        assertThat(solver.fractionToDecimal(400, 333), is(equalTo("1.(201)")));
    }

    @Test
    public void happy5() {
        var solver = new Fraction2RecurringDecimal();
        assertThat(solver.fractionToDecimal(-2, 1), is(equalTo("-2")));
    }

    @Test
    public void happy6() {
        var solver = new Fraction2RecurringDecimal();
        assertThat(solver.fractionToDecimal(1, 6), is(equalTo("0.1(6)")));
    }

    @Test
    public void happy7() {
        var solver = new Fraction2RecurringDecimal();
        assertThat(solver.fractionToDecimal(-50, 8), is(equalTo("-6.25")));
    }

    @Test
    public void happy8() {
        var solver = new Fraction2RecurringDecimal();
        assertThat(solver.fractionToDecimal(-2147483648, -1), is(equalTo("2147483648")));
    }
}
