package security;

import org.bouncycastle.openssl.jcajce.JcaPEMWriter;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import java.io.IOException;
import java.io.StringWriter;
import java.security.PublicKey;
import java.security.cert.Certificate;

/**
 * https://stackoverflow.com/questions/29559215/how-can-i-get-the-public-key-of-a-secure-webpage-using-java
 */
public class PublicKeyLab {
    public static void main(String[] args) throws IOException {
        String hostname = "www.google.com";
        SSLSocketFactory factory = HttpsURLConnection.getDefaultSSLSocketFactory();
        SSLSocket socket = (SSLSocket) factory.createSocket(hostname, 443);
        socket.startHandshake();
        Certificate[] certs = socket.getSession().getPeerCertificates();
        Certificate cert = certs[0];
        PublicKey key = cert.getPublicKey();
        System.out.println(key.toString());

        StringWriter stringWriter = new StringWriter();
        JcaPEMWriter pemWriter = new JcaPEMWriter(stringWriter);
        pemWriter.writeObject(key);
        pemWriter.close();
        System.out.println(stringWriter.toString());
    }
}
