package security;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESKeyGenTool {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(256);
        SecretKey key = kg.generateKey();

        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.printf("Generated AES Key (Base64): %s%n", base64Key);
    }
}
