package jjwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.util.Base64;

public class ParseTests {
    @Test
    void test_happy() {
        final String jwt = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJURVJFTkNFLkNIQU8iLCJ1c2VybmFtZSI6IlRFUkVOQ0UuQ0hBTyIsImRhdGEiOiJURVJFTkNFLkNIQU8iLCJpc3MiOiJEZWx0YURldk9wc0l0U2luZ2xlU2lnbk9uIiwiZXhwIjoxNzA1NDgwNjQ5LCJpYXQiOjE3MDU0NTE2Njl9.6mre53DyymvrCdow_rbEaJ77esU86qLlasGP3ekTQKM";
        String[] parts = jwt.split("\\.");
        String header = new String(Base64.getUrlDecoder().decode(parts[0]));
        System.out.println("Header: " + header);

        String payload = new String(Base64.getUrlDecoder().decode(parts[1]));
        System.out.println("Payload: " + payload);
    }
}
