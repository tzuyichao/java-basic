package brute;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchInRotatedSortedArrayTest {
    SearchInRotatedSortedArray solver;

    @Before
    public void init() {
        solver = new SearchInRotatedSortedArray();
    }

    @Test
    public void testcase1() {
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        int r = solver.search(nums, 0);
        assertThat(r).isEqualTo(4);
    }

    @Test
    public void testcase2() {
        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
        int r = solver.search(nums, 3);
        assertThat(r).isEqualTo(-1);
    }

    @Test
    public void testcase3() {
        int[] nums = new int[]{1};
        int r = solver.search(nums, 3);
        assertThat(r).isEqualTo(-1);
    }

    @Test
    public void testcase4() {
        int[] nums = new int[]{4, 5, 6, 0};
        int r = solver.search(nums, 5);
        assertThat(r).isEqualTo(1);
    }
}
