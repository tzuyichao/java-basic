package sort;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class InsertionSortTests {
    @Test
    void happy1() {
        int[] data = new int[]{5, 4, 3, 2, 1};
        int[] expected = new int[]{1, 2, 3, 4, 5};
        InsertionSort.sort(data);

        assertThat(data, is(expected));
    }
}
