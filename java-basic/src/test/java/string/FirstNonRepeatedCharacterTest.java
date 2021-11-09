package string;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FirstNonRepeatedCharacterTest {
    @Test
    public void testHappy() {
        String subject = "ABBY";
        char result = FirstNonRepeatedCharacter.find(subject);
        System.out.printf("%h%n", result);
        assertThat(result)
                .isEqualTo('A');
    }

    @Test
    public void testNotFound() {
        String subject = "AYBBBYA";
        char result = FirstNonRepeatedCharacter.find(subject);
        assertThat(result)
                .isEqualTo(Character.MIN_VALUE);
    }

    @Test
    public void testHappy2() {
        String subject = "ABBY";
        char result = FirstNonRepeatedCharacter.find2(subject);
        System.out.printf("%h%n", result);
        assertThat(result)
                .isEqualTo('A');
    }

    @Test
    public void testNotFound2() {
        String subject = "AYBBBYA";
        char result = FirstNonRepeatedCharacter.find2(subject);
        assertThat(result)
                .isEqualTo(Character.MIN_VALUE);
    }
}
