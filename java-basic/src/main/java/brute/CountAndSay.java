package brute;

/**
 * 38. Count and Say
 * 
 * Runtime: 3 ms, faster than 83.78% of Java online submissions for Count and Say.
 * Memory Usage: 42.5 MB, less than 27.97% of Java online submissions for Count and Say.
 */
public class CountAndSay {
    public String countAndSay(int n) {
        if(1 == n) {
            return "1";
        } else {
            String value = countAndSay(n-1);
            return say(value);
        }
    }

    public String say(String str) {
        if(str.length() == 0) {
            return str;
        }
        if(str.length() == 1) {
            return "1" + str;
        }
        char[] chars = str.toCharArray();
        char current = chars[0];
        StringBuilder result = new StringBuilder();
        int count = 1;
        for(int i=1; i<str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == current) {
                count++;
            } else {
                result.append(count).append(current);
                current = ch;
                count = 1;
            }
        }
        result.append(count).append(current);

        return result.toString();
    }
}
