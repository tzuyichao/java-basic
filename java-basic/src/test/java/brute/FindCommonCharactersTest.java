package brute;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.fail;


public class FindCommonCharactersTest {
    FindCommonCharacters solver;

    @Before
    public void init() {
        solver = new FindCommonCharacters();
    }

    private void assertExpect(String[] exp, List<String> actual) {
        for(String str : exp) {
            int idx = actual.indexOf(str);
            if(idx == -1) {
                fail("not found");
            } else {
                actual.remove(idx);
            }
        }
        if(actual.size() != 0) {
            fail("exist more element");
        }
    }

    @Test
    public void simple1() {
        String[] strs = new String[]{"bella","label","roller"};
        String[] expects = new String[] {"e","l","l"};

        List<String> actual = solver.commonChars(strs);
        assertExpect(expects, actual);
    }

    @Test
    public void simple2() {
        String[] strs = new String[]{"cool","lock","cook"};
        String[] expects = new String[] {"c","o"};

        List<String> actual = solver.commonChars(strs);
        assertExpect(expects, actual);
    }
}
