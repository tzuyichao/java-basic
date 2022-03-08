package brute;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class RepeatedDNASequencesTest {
    @Test
    public void happy1() {
        var solver = new RepeatedDNASequences();
        assertThat(solver.findRepeatedDnaSequences("AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"), is(equalTo(List.of("AAAAACCCCC", "CCCCCAAAAA"))));
    }

    @Test
    public void happy2() {
        var solver = new RepeatedDNASequences();
        assertThat(solver.findRepeatedDnaSequences("AAAA"), is(equalTo(Collections.EMPTY_LIST)));
    }

    @Test
    public void happy3() {
        var solver = new RepeatedDNASequences();
        assertThat(solver.findRepeatedDnaSequences("AAAAAAAAAA"), is(equalTo(Collections.EMPTY_LIST)));
    }

    @Test
    public void happy4() {
        var solver = new RepeatedDNASequences();
        assertThat(solver.findRepeatedDnaSequences("AAAAAAAAAAA"), is(equalTo(List.of("AAAAAAAAAA"))));
    }

    @Test
    public void happy5() {
        var solver = new RepeatedDNASequences();
        assertThat(solver.findRepeatedDnaSequences("AAAAAAAAAAC"), is(equalTo(Collections.EMPTY_LIST)));
    }
}
