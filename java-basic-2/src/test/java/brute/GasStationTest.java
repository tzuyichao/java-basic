package brute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class GasStationTest {
    @Test
    public void happy1() {
        var solver = new GasStation();
        assertThat(solver.canCompleteCircuit(new int[] {1,2,3,4,5}, new int[] {3,4,5,1,2}), is(equalTo(3)));
    }

    @Test
    public void happy2() {
        var solver = new GasStation();
        assertThat(solver.canCompleteCircuit(new int[] {2, 3, 4}, new int[] {3,4,3}), is(equalTo(-1)));
    }
}
