package collection;

import collection.sort.Melon;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MyArraysTest {
    @Test
    public void test_containsElement_happy() {
        int[] arr = new int[]{4, 5, 7, 1, 2};
        assertThat(MyArrays.containsElement(arr, 7))
                .isTrue();
    }

    @Test
    public void test_containsElement_not_found() {
        int[] arr = new int[]{4, 5, 7, 1, 2};
        assertThat(MyArrays.containsElement(arr, 8))
                .isFalse();
    }

    @Test(expected = NullPointerException.class)
    public void test_containsElement_null_array() {
        MyArrays.containsElement(null, 0);
    }

    @Test
    public void test_generic_containsElement_happy() {
        Melon[] melons = new Melon[] {
                new Melon("cantaloupe", 5),
                new Melon("watermelon", 11),
                new Melon("hami", 2),
                new Melon("snap melon", 6)
        };
        assertThat(MyArrays.containsElement(melons, new Melon("watermelon", 13), (m1, m2) -> {
            return m1.getType().compareTo(m2.getType());
        }))
                .isTrue();
    }

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
