package lang;

import org.junit.jupiter.api.Test;

public class StringTests {
    private static String replaceFromEnd(String original, String target, String replacement) {
        int index = original.lastIndexOf(target);
        if (index == -1) {
            return original;
        }
        return original.substring(0, index) + replacement + original.substring(index + target.length());
    }

    @Test
    void replace_test() {
        String str1 = "ddd-kafka-account-kafka-account";
        String str2 = "ddd-kafka-account";

        String result1 = replaceFromEnd(str1, "kafka-account", "dev-kafka-account");
        String result2 = replaceFromEnd(str2, "kafka-account", "dev-kafka-account");

        System.out.println(result1);
        System.out.println(result2);
    }
}
