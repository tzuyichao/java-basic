package brute;

/**
 * = 58
 */
public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        if("".equals(s.trim())) {
            return 0;
        }
        int len = s.trim().length();
        int lastIndexOfSpace = s.trim().lastIndexOf(" ");
        if(lastIndexOfSpace == -1) {
            return s.trim().length();
        } else {
            return len - lastIndexOfSpace - 1;
        }
    }
}
