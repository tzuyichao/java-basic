package text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class MatchesLab1 {
    public static void main(String[] args) {
        String Str = new String("www.github.com");

        System.out.println(Str.matches("(.*)github(.*)"));

        System.out.println(Str.matches("(.*)google(.*)"));

        System.out.println(Str.matches("www(.*)"));

        String str = "Hello,World! in Java.";
        Pattern pattern = Pattern.compile("W(or)(ld!)");
        Matcher matcher = pattern.matcher(str);
        System.out.println("Matcher group count: " + matcher.groupCount());
        while(matcher.find()){
            System.out.println("Group 0:"+matcher.group(0));
            System.out.println("Group 1:"+matcher.group(1));
            System.out.println("Group 2:"+matcher.group(2));
            System.out.println("Start 0:"+matcher.start(0)+" End 0:"+matcher.end(0));
            System.out.println("Start 1:"+matcher.start(1)+" End 1:"+matcher.end(1));
            System.out.println("Start 2:"+matcher.start(2)+" End 2:"+matcher.end(2));
            System.out.println(str.substring(matcher.start(0),matcher.end(1)));
        }
    }
}
