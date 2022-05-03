package brute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ShortestUnsortedContinuousSubarrayTest {
    @Test
    public void test_happy1() {
        var solver = new ShortestUnsortedContinuousSubarray();
        assertThat(solver.findUnsortedSubarray(new int[] {2,6,4,8,10,9,15}), is(equalTo(5)));
    }

    @Test
    public void test_happy2() {
        var solver = new ShortestUnsortedContinuousSubarray();
        assertThat(solver.findUnsortedSubarray(new int[] {1, 2, 3, 4, 5}), is(equalTo(0)));
    }

    @Test
    public void test_happy3() {
        var solver = new ShortestUnsortedContinuousSubarray();
        assertThat(solver.findUnsortedSubarray(new int[] {1}), is(equalTo(0)));
    }

    @Test
    public void test_happy4() {
        var solver = new ShortestUnsortedContinuousSubarray();
        assertThat(solver.findUnsortedSubarray(new int[] {1, 2, 3, 5, 4}), is(equalTo(2)));
    }
}
