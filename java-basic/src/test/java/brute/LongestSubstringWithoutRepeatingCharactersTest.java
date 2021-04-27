package brute;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LongestSubstringWithoutRepeatingCharactersTest {
    private LongestSubstringWithoutRepeatingCharacters solver;
    @Before
    public void init() {
        solver = new LongestSubstringWithoutRepeatingCharacters();
    }

    @Test
    public void simple1() {
        assertThat(solver.lengthOfLongestSubstring("abcabcbb"))
                .isEqualTo(3);
    }

    @Test
    public void simple2() {
        assertThat(solver.lengthOfLongestSubstring("bbbbb"))
                .isEqualTo(1);
    }

    @Test
    public void simple3() {
        assertThat(solver.lengthOfLongestSubstring("pwwkew"))
                .isEqualTo(3);
    }

    @Test
    public void simple4() {
        assertThat(solver.lengthOfLongestSubstring(""))
                .isEqualTo(0);
    }

    @Test
    public void simple5() {
        assertThat(solver.lengthOfLongestSubstring("abcabcdbb"))
                .isEqualTo(4);
    }

    @Test
    public void simple6() {
        assertThat(solver.lengthOfLongestSubstring("au"))
                .isEqualTo(2);
    }

    @Test
    public void simple7() {
        assertThat(solver.lengthOfLongestSubstring("dvdf"))
                .isEqualTo(3);
    }
}
