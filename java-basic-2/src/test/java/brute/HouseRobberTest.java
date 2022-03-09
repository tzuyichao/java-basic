package brute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class HouseRobberTest {
    @Test
    public void happy1() {
        var solver = new HouseRobber();
        assertThat(solver.rob(new int[] {1,2,3,1}), is(equalTo(4)));
    }

    @Test
    public void happy2() {
        var solver = new HouseRobber();
        assertThat(solver.rob(new int[] {2,7,9,3,1}), is(equalTo(12)));
    }

    @Test
    public void happy3() {
        var solver = new HouseRobber();
        assertThat(solver.rob(new int[] {2,7,9,3,1,100}), is(equalTo(111)));
    }

    @Test
    public void happy4() {
        var solver = new HouseRobber();
        assertThat(solver.rob(new int[] {2}), is(equalTo(2)));
    }

    @Test
    public void happy5() {
        var solver = new HouseRobber();
        assertThat(solver.rob(new int[] {7,2}), is(equalTo(7)));
    }
}
