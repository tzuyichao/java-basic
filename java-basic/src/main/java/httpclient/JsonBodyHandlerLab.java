package httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static httpclient.SetHeader.HEADER_CONTENT_TYPE;
import static httpclient.SetHeader.HEADER_REFERER;

public class JsonBodyHandlerLab {
    private static HttpRequest makeRequest(String uri, String contentType, String referer) {
        return HttpRequest.newBuilder()
                .headers(HEADER_CONTENT_TYPE, contentType, HEADER_REFERER, referer)
                .uri(URI.create(uri))
                .build();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = makeRequest("https://reqres.in/api/users/2", "application/json", "https://reqres.in/");

        HttpResponse<User> response = client.send(request, JsonBodyHandler.jsonBodyHandler(User.class));

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("\nBody: " + response.body());
    }
}
