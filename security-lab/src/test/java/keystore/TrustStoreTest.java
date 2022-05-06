package keystore;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.PKIXParameters;

/**
 * 起因在於開發的過程中遇到啟動過程spring cloud config connection失敗，把log level設定至debug可以看到ref 1的log
 * 追查後發現預設/lib/security目錄裡的cacerts應該有問題
 * 我的解法也很鄉愿
 * 雖然這裡還停留在1.8，但是把自己玩的其他版本jdk的cacerts覆蓋過去就可以了。
 *
 * Ref1: https://www.baeldung.com/java-trustanchors-parameter-must-be-non-empty
 */
public class TrustStoreTest {
    private KeyStore getKeyStore() throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException {
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(null, "changeIt".toCharArray());
        return ks;
    }

    @Test
    public void test() throws Exception {
        KeyStore keyStore = getKeyStore();
        InvalidAlgorithmParameterException invalidAlgorithmParameterException =
                Assertions.assertThrows(InvalidAlgorithmParameterException.class, () -> new PKIXParameters(keyStore));
        Assertions.assertEquals("the trustAnchors parameter must be non-empty", invalidAlgorithmParameterException.getMessage());
    }
}
