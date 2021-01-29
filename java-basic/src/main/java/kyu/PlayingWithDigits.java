package kyu;

public class PlayingWithDigits {
    public int digPow(int n, int p) {
        int total = 0;
        String nStr = Integer.toString(n, 10);
        int nextP = p;
        for(int i = 0; i < nStr.length(); i++) {
            total += Math.pow(Integer.parseInt(String.valueOf(nStr.charAt(i))), nextP);
            nextP += 1;
        }

        int k = total / n;
        int check = total % n;
        if(check == 0) {
            return k;
        } else {
            return -1;
        }
    }
}
