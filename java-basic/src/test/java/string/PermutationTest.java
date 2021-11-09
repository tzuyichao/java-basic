package string;

import org.junit.Test;

public class PermutationTest {
    @Test
    public void testHappy() {
        String str = "abc";
        Permutation.permutationAndPrint(str);
    }

    @Test
    public void testHappy2() {
        String str = "abcd";
        Permutation.permutationAndPrint2(str);
    }
}
