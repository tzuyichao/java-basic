package regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReplaceLab {
    public static void main(String[] args) {
        String subject = "camelCaseMoreCases";
        System.out.println(subject.replaceAll("([A-Z])", " $1"));

        Pattern pattern = Pattern.compile("([A-Z])");
        Matcher matcher = pattern.matcher(subject);
        System.out.println(matcher.replaceAll(" $1"));
    }
}
