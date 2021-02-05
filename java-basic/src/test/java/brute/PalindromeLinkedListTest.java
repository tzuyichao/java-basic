package brute;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PalindromeLinkedListTest {
    PalindromeLinkedList solver;

    @Before
    public void init() {
        solver = new PalindromeLinkedList();
    }

    @Test
    public void simple1() {
        assertFalse(solver.isPalindrome(ListNodeHelper.make(new int[] {1, 2})));
    }

    @Test
    public void simple2() {
        assertTrue(solver.isPalindrome(ListNodeHelper.make(new int[] {})));
    }

    @Test
    public void simple3() {
        assertTrue(solver.isPalindrome(ListNodeHelper.make(new int[] {1, 2, 1})));
    }

    @Test
    public void simple4() {
        assertTrue(solver.isPalindrome(ListNodeHelper.make(new int[] {1, 2, 2, 1})));
    }

    @Test
    public void simple5() {
        assertTrue(solver.isPalindrome(ListNodeHelper.make(new int[] {1})));
    }
}
