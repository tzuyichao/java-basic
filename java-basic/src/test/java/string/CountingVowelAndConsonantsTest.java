package string;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CountingVowelAndConsonantsTest {
    @Test
    public void testHappy() {
        String subject = "happy";
        Pair<Integer, Integer> result = CountingVowelAndConsonants.count(subject);
        System.out.println(result);
        assertThat(result)
                .isNotNull()
                .satisfies(r -> {
                   r.equals(Pair.of(1, 4));
                });
    }
}
