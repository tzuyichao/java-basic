package security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.stream.IntStream;

public class SecretKeyLab {
    public static final String KEY_ALGORITHM = "AES";
    public static final int KEY_LENGTH = 256;
    public static final String CIPHER_NAME = "AES/CBC/PKCS5Padding";

    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(KEY_LENGTH);
        return keyGenerator.generateKey();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
        SecretKey key = generateKey();
        Cipher cipher = Cipher.getInstance(CIPHER_NAME);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        IntStream.range(0, 10).forEach(_ -> {
            System.out.println(HexFormat.of().formatHex(cipher.getIV()));
        });
    }
}
