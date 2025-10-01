package security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class PasswordEncryptor {
    private static final String ALGORITHM = "AES/GCM/NoPadding";
    private static final int GCM_TAG_LENGTH = 128;
    private static final int IV_LENGTH = 12;

    public static String encrypt(String plainText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);

        byte[] iv = new byte[IV_LENGTH];
        new SecureRandom().nextBytes(iv);

        cipher.init(Cipher.ENCRYPT_MODE, key, new GCMParameterSpec(GCM_TAG_LENGTH, iv));
        byte[] cipherText = cipher.doFinal(plainText.getBytes("UTF-8"));

        byte[] combined = new byte[iv.length + cipherText.length];
        System.arraycopy(iv, 0, combined, 0, iv.length);
        System.arraycopy(cipherText, 0, combined, iv.length, cipherText.length);

        return Base64.getEncoder().encodeToString(combined);
    }

    public static String decrypt(String base64CipherText, SecretKey key) throws Exception {
        byte[] decoded = Base64.getDecoder().decode(base64CipherText);

        byte[] iv = Arrays.copyOfRange(decoded, 0, IV_LENGTH);
        byte[] cipherText = Arrays.copyOfRange(decoded, IV_LENGTH, decoded.length);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key, new GCMParameterSpec(GCM_TAG_LENGTH, iv));

        byte[] plainBytes = cipher.doFinal(cipherText);
        return new String(plainBytes, "UTF-8");
    }

    public static void main(String[] args) throws Exception {
        SecretKey key = AesKeyLoader.loadFromEnv("KEY");

        String password = "my_secure_password";

        String encryptedPassword = encrypt(password, key);
        System.out.println("Encrypted Password: " + encryptedPassword);

        String decrypted = decrypt(encryptedPassword, key);
        System.out.println("Decrypted: " + decrypted);

        System.out.println(decrypt("2m3FVJQ/wbEBx5IUDHdNKzJbXES+8/lrHbnQL7MXTMrCvSD7vw==", key));
    }
}
