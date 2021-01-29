package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MergeSortedArrayTest {
    MergeSortedArray mergeSortedArray;

    @Before
    public void setup() {
        mergeSortedArray = new MergeSortedArray();
    }

    @Test
    public void basic() {
        int[] arr1 = new int[] {1, 2, 3, 0, 0, 0};
        int[] arr2 = new int[] {2, 5, 6};

        mergeSortedArray.merge(arr1, 3, arr2, 3);

        assertEquals(1, arr1[0]);
        assertEquals(2, arr1[1]);
        assertEquals(2, arr1[2]);
        assertEquals(3, arr1[3]);
        assertEquals(5, arr1[4]);
        assertEquals(6, arr1[5]);
    }

    @Test
    public void submission_fail_1() {
        int[] arr1 = new int[] {0};
        int[] arr2 = new int[] {1};

        mergeSortedArray.merge(arr1, 1, arr2, 1);
        assertEquals(1, arr1[0]);
    }

    @Test
    public void submission_fail_2() {
        int[] arr1 = new int[] {4, 0, 0, 0, 0, 0};
        int[] arr2 = new int[] {1, 2, 3, 5, 6};

        mergeSortedArray.merge(arr1, 1, arr2, 5);
        assertEquals(1, arr1[0]);
        assertEquals(2, arr1[1]);
        assertEquals(3, arr1[2]);
        assertEquals(4, arr1[3]);
        assertEquals(5, arr1[4]);
        assertEquals(6, arr1[5]);
    }
}
