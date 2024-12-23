package security.sasl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.callback.*;
import javax.security.sasl.AuthorizeCallback;
import javax.security.sasl.RealmCallback;
import java.io.IOException;

/**
 * Source: https://www.baeldung.com/java-sasl
 */
public class ServerCallbackHandler implements CallbackHandler {
    private static final Logger log = LoggerFactory.getLogger(ServerCallbackHandler.class);

    @Override
    public void handle(Callback[] cbs) throws IOException, UnsupportedCallbackException {
        for (Callback cb : cbs) {
            if (cb instanceof AuthorizeCallback acb) {
                String authid = acb.getAuthenticationID();
                String authzid = acb.getAuthorizationID();
                log.info("Authentication ID: {}", authid);
                log.info("Authorization ID: {}", authzid);
                if (authid.equals(authzid)) {
                    acb.setAuthorized(true);
                } else {
                    acb.setAuthorized(false);
                }
                if (acb.isAuthorized()) {
                    log.info("Authorization was successful");
                } else {
                    log.info("Authorization failed");
                }
            } else if (cb instanceof NameCallback nc) {
                log.info("NameCallback: {}", nc);
                log.info("Name: {}", nc.getName());
                log.info("Default Name: {}", nc.getDefaultName());
                log.info("Prompt: {}", nc.getPrompt());
                nc.setName("username1");
            } else if (cb instanceof PasswordCallback pc) {
                log.info("PasswordCallback: {}", pc);
                log.info("password: {}", pc.getPassword());
                pc.setPassword("password1".toCharArray());
            } else if (cb instanceof RealmCallback rc) {
                log.info("RealmCallback: {}", rc);
                rc.setText("myServer");
            } else {
                log.info("Unrecognized Callback: {}", cb.getClass().getName());
                throw new UnsupportedCallbackException(cb, "Unrecognized Callback");
            }
        }
    }
}
