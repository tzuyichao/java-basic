package basic;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CountingSortTest {
    @Test
    public void testHappy() {
        int[] subject = new int[]{4, 6, 2, 7, 1};
        CountingSort.sort(subject);
        assertThat(subject)
                .containsSequence(1, 2, 4, 6, 7);
    }
}
