package security;

import javax.crypto.SecretKey;
import java.util.Base64;

public class AesKeyLoader {
    public static SecretKey loadFromEnv(String envName) {
        String base64Key = System.getenv(envName);
        if (base64Key == null || base64Key.isEmpty()) {
            throw new IllegalArgumentException("Environment variable " + envName + " is not set or empty.");
        }

        byte[] raw = Base64.getDecoder().decode(base64Key);
        return new javax.crypto.spec.SecretKeySpec(raw, "AES");
    }
}
