package httpclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static httpclient.SetHeader.HEADER_CONTENT_TYPE;

public class PostJsonLab {
    private static final ObjectMapper mapper = new ObjectMapper();
    public static void main(String[] args) throws IOException, InterruptedException {
        Data data = new Data();

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .header(HEADER_CONTENT_TYPE, "application/json")
                .uri(URI.create("https://reqres.in/api/users"))
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(data)))
                .build();

        HttpResponse<Void> response = client.send(request, HttpResponse.BodyHandlers.discarding());

        System.out.println("Status Code: " + response.statusCode());
    }
}
