package collection;

import org.junit.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

public class ArraysTest {
    @Test
    public void test_first_mismatch_index() {
        int[] integers1 = {3, 4, 5, 6, 1, 5, 3, 4};
        int[] integers2 = {3, 4, 5, 6, 1, 5, 3, 4};
        int[] integers3 = {3, 4, 5, 6, 1, 3, 4, 4};

        assertThat(Arrays.mismatch(integers1, integers2))
                .isEqualTo(-1);
        assertThat(Arrays.mismatch(integers1, integers3))
                .isEqualTo(5);
    }

    @Test
    public void test_arrays_lexicographically() {
        int[] integers1 = {3, 4, 5, 6, 1, 5, 3, 4};
        int[] integers2 = {3, 4, 5, 6, 1, 5, 3, 4};
        int[] integers3 = {3, 4, 5, 6, 1, 3, 4, 4};

        assertThat(Arrays.compare(integers1, integers2))
                .isEqualTo(0);
        assertThat(Arrays.compare(integers1, integers3))
                .isEqualTo(1);
    }
}
