package org.example.ksqldb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * LIST STREAMS;
 * DROP STREAM <stream_name>;
 * DESCRIBE <stream_name>;
 * SHOW QUERIES;
 * TERMINATE <query_id>;
 ** CREATE STREAM <stream_name> AS SELECT * FROM <existing_stream_name> WHERE <condition>;
 ** INSERT INTO <stream_name>;
 * ASSERT TOPIC <topic_name>;
 */
public class KSQLQueryLab {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {
        System.out.println("KSQL Query lab");
        Dotenv dotenv = Dotenv.load();
        ObjectMapper objectMapper = new ObjectMapper();
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(3))
                .build();
        final String query = """
                {
                    "ksql": "DESCRIBE QUALITY_QUALITYALERT_PRODUCTLINEUNBLOCK_V0;",
                    "streamsProperties": {
                      "ksql.streams.auto.offset.reset": "earliest"
                    }
                }
                """;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(dotenv.get("KSQLDB_KSQL_URL")))
                .header("Content-Type", "application/vnd.ksql.v1+json")
                .POST(HttpRequest.BodyPublishers.ofString(query))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status Code:" + response.statusCode());
        JsonNode resultObj = objectMapper.readValue(response.body(), JsonNode.class);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(resultObj));
    }
}
