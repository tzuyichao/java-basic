package security.sasl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.sasl.Sasl;
import javax.security.sasl.SaslClient;
import javax.security.sasl.SaslException;
import javax.security.sasl.SaslServer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * from https://github.com/eugenp/tutorials/blob/master/core-java-modules/core-java-security/src/test/java/com/baeldung/sasl/SaslUnitTest.java
 */
public class SaslUnitTest {
    private static final Logger log = LoggerFactory.getLogger(SaslUnitTest.class);

    private static final String MECHANISM = "DIGEST-MD5";
    private static final String SERVER_NAME = "myServer";
    private static final String PROTOCOL = "myProtocol";
    private static final String AUTHORIZATION_ID = null;
    private static final String QOP_LEVEL = "auth-conf";

    private SaslServer saslServer;
    private SaslClient saslClient;

    @BeforeEach
    void setup() throws SaslException {
        ServerCallbackHandler serverCallbackHandler = new ServerCallbackHandler();
        ClientCallbackHandler clientCallbackHandler = new ClientCallbackHandler();

        Map<String, String> props = new HashMap<>();
        props.put(Sasl.QOP, QOP_LEVEL);

        saslServer = Sasl.createSaslServer(MECHANISM, PROTOCOL, SERVER_NAME, props, serverCallbackHandler);
        saslClient = Sasl.createSaslClient(new String[] { MECHANISM }, AUTHORIZATION_ID, PROTOCOL, SERVER_NAME, props, clientCallbackHandler);
    }

    @Test
    void givenHandlers_whenStarted_thenAuthenticationWorks() throws SaslException {
        byte[] challenge;
        byte[] response;

        log.info("1");
        challenge = saslServer.evaluateResponse(new byte[0]);
        log.info("2: server challenge: {}", new String(challenge, StandardCharsets.UTF_8));
        response = saslClient.evaluateChallenge(challenge);
        log.info("3: client response: {}", new String(response, StandardCharsets.UTF_8));
        challenge = saslServer.evaluateResponse(response);
        log.info("4: server challenge: {}", new String(challenge, StandardCharsets.UTF_8));
        response = saslClient.evaluateChallenge(challenge);
        if(response != null) {
            log.info("5: client response: {}", new String(response, StandardCharsets.UTF_8));
        }

        log.info("Server is complete");
        assertTrue(saslServer.isComplete());
        assertTrue(saslClient.isComplete());

        log.info("qop property");
        String qop = (String) saslClient.getNegotiatedProperty(Sasl.QOP);
        assertEquals("auth-conf", qop);

        log.info("Wrap and Unwrap");
        byte[] outgoing = "Baeldung".getBytes();
        byte[] secureOutgoing = saslClient.wrap(outgoing, 0, outgoing.length);

        byte[] secureIncoming = secureOutgoing;
        byte[] incoming = saslServer.unwrap(secureIncoming, 0, secureIncoming.length);
        assertEquals("Baeldung", new String(incoming, StandardCharsets.UTF_8));
    }

    @AfterEach
    void tearDown() throws SaslException {
        saslClient.dispose();
        saslServer.dispose();
    }
}
