package brute;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class FindAndReplaceInStringTest {
    @Test
    public void happy1() {
        var solver = new FindAndReplaceInString();
        assertThat(
                solver.findReplaceString(
                        "abcd",
                        new int[] {0, 2},
                        new String[] {"a", "cd"},
                        new String[] {"eee", "ffff"}),
                is(equalTo("eeebffff")));
    }

    @Test
    public void happy2() {
        var solver = new FindAndReplaceInString();
        assertThat(
                solver.findReplaceString(
                        "abcd",
                        new int[] {0, 2},
                        new String[] {"ab", "ec"},
                        new String[] {"eee", "ffff"}),
                is(equalTo("eeecd")));
    }

    @Test
    public void happy3() {
        var solver = new FindAndReplaceInString();
        assertThat(
                solver.findReplaceString(
                        "vmokgggqzp",
                        new int[] {3, 5, 1},
                        new String[] {"kg","ggq","mo"},
                        new String[] {"s","so","bfr"}),
                is(equalTo("vbfrssozp")));
    }
}
