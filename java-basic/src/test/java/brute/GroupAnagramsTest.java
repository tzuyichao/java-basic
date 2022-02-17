package brute;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GroupAnagramsTest {
    @Test
    public void test_profile_1() {
        GroupAnagrams solver = new GroupAnagrams();

        assertThat(solver.profile("bdddddddddd").equals(solver.profile("bbbbbbbbbbc")))
                .isFalse();
    }
}
