package brute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

public class MedianFinderTest {
    @Test
    public void happy1() {
        var solver = new MedianFinder();
        solver.addNum(1);
        solver.addNum(2);
        assertThat(solver.findMedian(), is(equalTo(1.5)));
        solver.addNum(3);
        assertThat(solver.findMedian(), is(equalTo(2.0)));
    }
}
