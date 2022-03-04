package brute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class MaximumProductSubarrayTest {
    @Test
    public void happy1() {
        var solver = new MaximumProductSubarray();
        assertThat(solver.maxProduct(new int[] {2,3,-2,4}), is(equalTo(6)));
    }

    @Test
    public void happy2() {
        var solver = new MaximumProductSubarray();
        assertThat(solver.maxProduct(new int[] {-2,0,-1}), is(equalTo(0)));
    }

    @Test
    public void happy3() {
        var solver = new MaximumProductSubarray();
        assertThat(solver.maxProduct(new int[] {2,3,2,4}), is(equalTo(48)));
    }

    @Test
    public void happy4() {
        var solver = new MaximumProductSubarray();
        assertThat(solver.maxProduct(new int[] {6, -3, -10, 0, 2}), is(equalTo(180)));
    }
}
