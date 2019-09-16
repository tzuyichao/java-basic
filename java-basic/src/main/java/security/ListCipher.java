package security;

import java.security.Security;

public class ListCipher {
    public static void main(String[] args) {
        for(Object obj : Security.getAlgorithms("Cipher")) {
            System.out.println(obj);
        }
    }
}
