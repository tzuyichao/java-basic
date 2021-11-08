package string;

import org.junit.Test;

import java.nio.charset.Charset;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class CountingDuplicateCharactersTest {
    @Test
    public void testHappy() {
        String target = "Happy";
        Map<String, Long> result = CountingDuplicateCharacters.count(target);
        assertThat(result)
                .isNotNull()
                .satisfies(r -> {
                    System.out.println(r);
                    assertThat(r.get("H"))
                            .isEqualTo(1);
                    assertThat(r.get("p"))
                            .isEqualTo(2);
                });
    }

    @Test
    public void testHappyUnicode() {
        String target = "Happy測試aBc";
        for(int i=0; i<target.length(); i++) {
            System.out.println(target.charAt(i) + " " + target.codePointAt(i));
        }
        Map<String, Long> result = CountingDuplicateCharacters.count(target);
        assertThat(result)
                .isNotNull()
                .satisfies(r -> {
                    System.out.println(r);
                    assertThat(r.get("H"))
                            .isEqualTo(1);
                    assertThat(r.get("測"))
                            .isEqualTo(1);
                    assertThat(r.get("p"))
                            .isEqualTo(2);
                });
    }

    @Test
    public void testHappyUnicode2() {
        String target = "測試";
        System.out.println(Charset.defaultCharset());
        System.out.println(target.split("").length + " " + target.length());
        for(int i=0; i<target.length(); i++) {
            System.out.println(target.charAt(i) + " " + target.codePointAt(i));
        }
        Map<String, Long> result = CountingDuplicateCharacters.count(target);
        assertThat(result)
                .isNotNull()
                .satisfies(r -> {
                    System.out.println(r);
                    assertThat(r.get("測"))
                            .isEqualTo(1);
                });
    }
}
