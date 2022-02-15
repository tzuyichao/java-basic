package basic;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArrayLabTest {
    @Test
    public void testMul_happy1() {
        ArrayLab solver = new ArrayLab();
        assertThat(solver.mul(new int[] {9, 9, 9}, new int[] {9}))
                .isEqualTo(new int[]{8, 9, 9, 1});

    }

    @Test
    public void testMul_happy2() {
        ArrayLab solver = new ArrayLab();
        assertThat(solver.mul(new int[] {9, 9, 9}, new int[] {9, 9}))
                .isEqualTo(new int[]{9, 8, 9, 0, 1});

    }

    @Test
    public void testMul_happy3() {
        ArrayLab solver = new ArrayLab();
        assertThat(solver.mul(new int[] {9, 9, 9}, new int[] {9, 9, 9}))
                .isEqualTo(new int[]{9, 9, 8, 0, 0, 1});

    }
}
