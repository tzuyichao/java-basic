package security;

import org.bouncycastle.util.encoders.Hex;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public final class SecretKeyUtil {
    public static String convertSecretKeyToBase64String(SecretKey secretKey) {
        byte[] data = secretKey.getEncoded();
        return Base64.getEncoder().encodeToString(data);
    }
    public static SecretKey convertBase64StringToSecretKey(String encodedKey, String algorithm) {
        byte[] data = Base64.getDecoder().decode(encodedKey);
        return new SecretKeySpec(data, 0, data.length, algorithm);
    }

    public static String convertSecretKeyToHexString(SecretKey secretKey) {
        byte[] data = secretKey.getEncoded();
        return Hex.toHexString(data);
    }
    public static SecretKey convertHexStringToSecretKey(String encodedKey, String algorithm) {
        byte[] data = Hex.decode(encodedKey);
        return new SecretKeySpec(data, 0, data.length, algorithm);
    }
}
