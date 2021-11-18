package brute;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MaxAreaTest {
    @Test
    public void testHappy1() {
        MaxArea solution = new MaxArea();
        assertThat(solution.maxArea(new int[] {1,8,6,2,5,4,8,3,7}))
                .isEqualTo(49);
    }

    @Test
    public void testHappy2() {
        MaxArea solution = new MaxArea();
        assertThat(solution.maxArea(new int[] {4,3,2,1,4}))
                .isEqualTo(16);
    }

    @Test
    public void testHappy3() {
        MaxArea solution = new MaxArea();
        assertThat(solution.maxArea(new int[] {1,2,1}))
                .isEqualTo(2);
    }

    @Test
    public void testHappy4() {
        MaxArea solution = new MaxArea();
        assertThat(solution.maxArea(new int[] {1,1}))
                .isEqualTo(1);
    }
}
