package collection;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyArraysTest {
    @Test
    public void test_findIndexOfElement_happy() {
        int[] arr = new int[]{4, 5, 7, 1, 2};
        assertThat(MyArrays.findIndexOfElement(arr, 7))
                .isEqualTo(2);
    }

    @Test
    public void test_findIndexOfElement_not_found() {
        int[] arr = new int[]{4, 5, 7, 1, 2};
        assertThat(MyArrays.findIndexOfElement(arr, 8))
                .isEqualTo(-1);
    }

    @Test(expected = NullPointerException.class)
    public void test_findIndexOfElement_null_array() {
        MyArrays.findIndexOfElement(null, 0);
    }
}
