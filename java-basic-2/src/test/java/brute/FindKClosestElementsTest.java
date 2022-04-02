package brute;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;

public class FindKClosestElementsTest {
    @Test
    public void happy1() {
        var solver = new FindKClosestElements();
        assertThat(solver.findClosestElements(new int[]{1,2,3,4,5}, 4, 3), is(equalTo(List.of(1,2,3,4))));
    }
}
