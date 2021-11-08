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
}
