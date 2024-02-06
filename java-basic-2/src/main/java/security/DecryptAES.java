package security;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class DecryptAES {
    private static final byte[] LOCAL_KEY_CACHE = new byte[] { -70, -69, 74, -97, 119, 74, -72, 83, -55, 108, 45, 101, 61, -2, 84, 74 };
    public static final String KEY_ALGORITHM = "AES";
    public static final String CIPHER_NAME = "AES/CBC/PKCS5Padding";

    public static SecretKey getLocalSecretKey() {
        return new SecretKeySpec(LOCAL_KEY_CACHE, KEY_ALGORITHM);
    }

    public static void main(String[] args) throws IOException {
        byte[] data = Files.readAllBytes(Paths.get("D:\\credentials-config.json"));

        try (InputStream byteStream = new ByteArrayInputStream(data)) {
            Cipher cipher = Cipher.getInstance(CIPHER_NAME);

            byte[] fileIv = new byte[16];
            byteStream.read(fileIv);
            cipher.init(Cipher.DECRYPT_MODE, getLocalSecretKey(), new IvParameterSpec(fileIv));

            try (CipherInputStream cipherIn = new CipherInputStream(byteStream, cipher)) {
                ByteArrayOutputStream resultBuffer = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int bytesRead;
                while((bytesRead = cipherIn.read(buffer)) != -1) {
                    resultBuffer.write(buffer, 0, bytesRead);
                }
                String content = new String(resultBuffer.toByteArray());
                System.out.println(content);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (InvalidAlgorithmParameterException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }
}
