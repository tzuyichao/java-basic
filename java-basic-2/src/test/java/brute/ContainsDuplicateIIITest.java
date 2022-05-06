package brute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ContainsDuplicateIIITest {
    @Test
    public void happy1() {
        var solver = new ContainsDuplicateIII();
        assertThat(solver.containsNearbyAlmostDuplicate(new int[] {-2147483648, 2147483647}, 1, 1), is(false));
    }
}
