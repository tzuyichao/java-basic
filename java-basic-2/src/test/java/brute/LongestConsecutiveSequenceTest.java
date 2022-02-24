package brute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class LongestConsecutiveSequenceTest {
    @Test
    public void happy1() {
        var solver = new LongestConsecutiveSequence();
        assertThat(solver.longestConsecutive(new int[] {100, 4, 200, 1, 3, 2}), is(equalTo(4)));
    }

    @Test
    public void happy2() {
        var solver = new LongestConsecutiveSequence();
        assertThat(solver.longestConsecutive(new int[] {0, 3, 7, 2, 5, 8, 4, 6, 0, 1}), is(equalTo(9)));
    }

    @Test
    public void happy3() {
        var solver = new LongestConsecutiveSequence();
        assertThat(solver.longestConsecutive(new int[] {100,4,200,1,3,2,101,7,102,103,104,105}), is(equalTo(6)));
    }
}
