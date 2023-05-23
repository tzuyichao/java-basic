package commons_net;

import org.apache.commons.net.whois.WhoisClient;

import java.io.IOException;
import java.net.SocketException;

public class WhoisClientMain {
    public static void main(String[] args) {
        WhoisClient whoisClient = new WhoisClient();
        try {
            whoisClient.connect(WhoisClient.DEFAULT_HOST);
            String result = whoisClient.query("google.com");
            System.out.println(result);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                whoisClient.disconnect();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
