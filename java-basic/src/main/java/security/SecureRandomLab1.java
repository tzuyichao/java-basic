package security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecureRandomLab1 {
    public static void main(String[] args) {
        try {
            SecureRandom secureRandom = SecureRandom.getInstance("NativePRNG");
            secureRandom.setSeed(System.currentTimeMillis());
            for(int i=0; i<10; i++) {
                System.out.println(secureRandom.nextInt(100));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
