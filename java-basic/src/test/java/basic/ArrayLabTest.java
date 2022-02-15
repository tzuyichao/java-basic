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

    @Test
    public void testMul_happy4() {
        ArrayLab solver = new ArrayLab();
        assertThat(solver.mul(new int[] {9}, new int[] {9}))
                .isEqualTo(new int[]{8, 1});

    }

    @Test
    public void testMul_happy5() {
        ArrayLab solver = new ArrayLab();
        assertThat(solver.mul(new int[] {2}, new int[] {3}))
                .isEqualTo(new int[]{0, 6});

    }

    @Test
    public void testToIntArray_happy1() {
        ArrayLab solver = new ArrayLab();
        assertThat(solver.toIntArray("123"))
                .isEqualTo(new int[]{1, 2, 3});
    }

    @Test
    public void testToIntArray_happy2() {
        ArrayLab solver = new ArrayLab();
        assertThat(solver.toIntArray("2"))
                .isEqualTo(new int[]{2});
    }
}
