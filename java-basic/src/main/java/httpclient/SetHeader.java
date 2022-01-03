package httpclient;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SetHeader {
    public static final String HEADER_CONTENT_TYPE = "Content-Type";
    public static final String HEADER_REFERER = "Referer";

    private static HttpRequest make1(String uri, String contentType, String referer) {
        return HttpRequest.newBuilder()
                .header(HEADER_CONTENT_TYPE, contentType)
                .header(HEADER_REFERER, referer)
                .uri(URI.create(uri))
                .build();
    }

    private static HttpRequest make2(String uri, String contentType, String referer) {
        return HttpRequest.newBuilder()
                .setHeader(HEADER_CONTENT_TYPE, contentType)
                .setHeader(HEADER_REFERER, referer)
                .uri(URI.create(uri))
                .build();
    }

    private static HttpRequest make3(String uri, String contentType, String referer) {
        return HttpRequest.newBuilder()
                .headers(HEADER_CONTENT_TYPE, contentType, HEADER_REFERER, referer)
                .uri(URI.create(uri))
                .build();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = make3("https://reqres.in/api/users/2", "application/json", "https://reqres.in/");

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status Code: " + response.statusCode());
        System.out.println("\nBody: " + response.body());
    }
}
