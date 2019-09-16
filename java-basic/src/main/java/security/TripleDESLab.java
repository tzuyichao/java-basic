package security;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;

public class TripleDESLab {
    private static final int ITERATION = 500;
    private static final String ALGORITHM = "PBEWithMD5AndTripleDES";

    private String password;
    private byte[] salt;
    private SecretKeyFactory secretKeyFactory;
    private Cipher cipher;
    private Key key;
    private AlgorithmParameterSpec algorithmParameterSpec;

    public TripleDESLab(String password) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeySpecException {
        this.password = password;
        // generate salt
        salt = new byte[8];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);

        secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        cipher = Cipher.getInstance(ALGORITHM);
        int iteration = ITERATION;
        char[] pwd = password.toCharArray();
        algorithmParameterSpec = new PBEParameterSpec(salt, iteration);
        PBEKeySpec pbeKeySpec = new PBEKeySpec(pwd, salt, iteration);
        key = secretKeyFactory.generateSecret(pbeKeySpec);
    }

    public byte[] encrypt(String source) throws InvalidKeySpecException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        cipher.init(Cipher.ENCRYPT_MODE, key, algorithmParameterSpec);
        return cipher.doFinal(source.getBytes());
    }

    public byte[] decrypt(byte[] target) throws InvalidKeySpecException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {
        int iteration = ITERATION;
        cipher.init(Cipher.DECRYPT_MODE, key, algorithmParameterSpec);
        return cipher.doFinal(target);
    }

    public byte[] getSalt() {
        return salt.clone();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeySpecException, InvalidKeyException, InvalidAlgorithmParameterException {
        TripleDESLab tripleDESLab = new TripleDESLab("password");
        byte[] salt = tripleDESLab.getSalt();
//        salt[0] = 10;
        System.out.println(Hex.encodeHexString(salt));

        final String target = "2171";
        byte[] encrypted = tripleDESLab.encrypt(target);
        System.out.println("Text: " + target);
        System.out.println("Encrypted: " + Hex.encodeHexString(encrypted));
        System.out.println("Decrypted: " + new String(tripleDESLab.decrypt(encrypted)));
    }
}
