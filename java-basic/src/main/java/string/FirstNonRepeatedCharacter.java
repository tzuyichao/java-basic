package string;

public class FirstNonRepeatedCharacter {
    public static final int EXTENDED_ASCII_CODES = 256;

    public static char find(String subject) {
        int[] flags = new int[EXTENDED_ASCII_CODES];
        for(int i=0; i<EXTENDED_ASCII_CODES; i++) {
            flags[i] = -1;
        }

        for(int i=0; i<subject.length(); i++) {
            char ch = subject.charAt(i);
            if(flags[ch] == -1) {
                flags[ch] = i;
            } else {
                flags[ch] = -2;
            }
        }
        int position = Integer.MAX_VALUE;

        for(int i=0; i<EXTENDED_ASCII_CODES; i++) {
            if(flags[i] >= 0) {
                position = Math.min(position, flags[i]);
            }
        }

        return position == Integer.MAX_VALUE?Character.MIN_VALUE:subject.charAt(position);
    }
}
