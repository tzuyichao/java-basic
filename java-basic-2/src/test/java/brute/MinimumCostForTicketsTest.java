package brute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class MinimumCostForTicketsTest {
    @Test
    public void test_happy_1() {
        MinimumCostForTickets solver = new MinimumCostForTickets();
        assertThat(solver.mincostTickets(new int[] {1,2,3,4,5,6,7,8,9,10,30,31}, new int[] {2, 7, 15}), is(equalTo(17)));
    }
}
