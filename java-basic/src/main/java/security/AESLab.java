package security;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AESLab {
    private KeyGenerator keyGenerator;
    private SecretKey secretKey;
    private Cipher cipher;

    public AESLab() throws NoSuchAlgorithmException, NoSuchPaddingException {
        keyGenerator = KeyGenerator.getInstance("AES");
        secretKey = keyGenerator.generateKey();
        cipher = Cipher.getInstance("AES");
    }

    public byte[] encrypt(String source) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] src = source.getBytes();
        byte[] encrypted = cipher.doFinal(src);
        return encrypted;
    }

    public byte[] decrypt(byte[] target) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decrypted = cipher.doFinal(target);
        return decrypted;
    }

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        AESLab aesLab = new AESLab();
        final String target = "2171";
        byte[] encrypted = aesLab.encrypt(target);
        byte[] decrypted = aesLab.decrypt(encrypted);

        System.out.println("Text: " + target);
        System.out.println("Encrypted: " + Hex.encodeHexString(encrypted));
        System.out.println("Decrypted: " + new String(decrypted));
    }
}
