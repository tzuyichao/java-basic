package brute;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JumpGameTest {
    @Test
    public void test_happy1() {
        JumpGame solver = new JumpGame();
        assertThat(solver.canJump(new int[] {0}))
                .isTrue();
    }

    @Test
    public void test_happy2() {
        JumpGame solver = new JumpGame();
        assertThat(solver.canJump(new int[] {2, 3, 1, 1, 4}))
                .isTrue();
    }

    @Test
    public void test_happy3() {
        JumpGame solver = new JumpGame();
        assertThat(solver.canJump(new int[] {3, 2, 1, 0, 4}))
                .isFalse();
    }
}
