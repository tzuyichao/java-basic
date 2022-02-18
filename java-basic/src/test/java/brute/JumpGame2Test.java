package brute;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JumpGame2Test {
    @Test
    public void test_happy_1() {
        JumpGame2 solver = new JumpGame2();
        assertThat(solver.jump(new int[] {2,3,1,1,4}))
                .isEqualTo(2);
    }
}
