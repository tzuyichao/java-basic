package httpclient;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.http.HttpResponse;

public class JsonBodyHandler<T> implements HttpResponse.BodyHandler<T> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Class<T> type;

    private JsonBodyHandler(Class<T> type) {
        this.type = type;
    }

    public static <T> JsonBodyHandler<T> jsonBodyHandler(Class<T> type) {
        return new JsonBodyHandler<>(type);
    }

    @Override
    public HttpResponse.BodySubscriber<T> apply(HttpResponse.ResponseInfo responseInfo) {
        return HttpResponse.BodySubscribers.mapping(HttpResponse.BodySubscribers.ofByteArray(), bytes -> {
            try {
                return objectMapper.readValue(bytes, type);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        });
    }
}
