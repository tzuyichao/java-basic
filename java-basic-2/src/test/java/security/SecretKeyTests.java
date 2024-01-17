package security;

import org.bouncycastle.util.encoders.Hex;
import org.junit.jupiter.api.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

/**
 * https://www.baeldung.com/java-secret-key-to-string
 */
public class SecretKeyTests {
    public SecretKey generateKeyFromSecureRandom(int size) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(size);
        return keyGenerator.generateKey();
    }

    @Test
    void test_secure_random_happy() throws NoSuchAlgorithmException {
        SecretKey secretKey = generateKeyFromSecureRandom(256);
        System.out.println(secretKey.getAlgorithm());
        System.out.println(secretKey.getFormat());
        System.out.println(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
        System.out.println(Hex.toHexString(secretKey.getEncoded()));
    }

    public SecretKey getKeyFromPassword(String password, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 256);
        return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
    }

    @Test
    void test_password_based_key_derivation_function_happy() throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKey secretKey = getKeyFromPassword("test", "salt");
        System.out.println(secretKey.getAlgorithm());
        System.out.println(secretKey.getFormat());
        System.out.println(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
        System.out.println(Hex.toHexString(secretKey.getEncoded()));
    }
}
