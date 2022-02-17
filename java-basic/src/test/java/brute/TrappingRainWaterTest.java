package brute;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TrappingRainWaterTest {

    @Test
    public void test_volume() {
        var data = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
        TrappingRainWater solver = new TrappingRainWater();
        assertThat(solver.volume(data, 3, 7))
                .isEqualTo(4);
    }

    @Test
    public void test_trap_1() {
        var data = new int[] {0,1,0,2,1,0,1,3,2,1,2,1};
        TrappingRainWater solver = new TrappingRainWater();
        assertThat(solver.trap(data))
                .isEqualTo(6);
    }
}
