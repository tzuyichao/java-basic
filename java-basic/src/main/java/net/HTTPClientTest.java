package net;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

public class HTTPClientTest {
    public static void main(String[] args) throws URISyntaxException {
        var client = HttpClient.newBuilder().build();
        var uri = new URI("https://google.com");
        var request = HttpRequest.newBuilder(uri).build();
        var handler = HttpResponse.BodyHandlers.ofString();

        CompletableFuture.allOf(
                client.sendAsync(request, handler).thenAccept((resp) -> System.out.println(resp.body())),
                client.sendAsync(request, handler).thenAccept((resp) -> System.out.println(resp.body())),
                client.sendAsync(request, handler).thenAccept((resp) -> System.out.println(resp.body()))
        ).join();
    }
}
