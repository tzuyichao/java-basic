package org.example.ksqldb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class KsqlMetadataExporter {
    private static String KSQLDB_SERVER = null;
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final OkHttpClient client = new OkHttpClient();

    private static final String BACKUP_DIR = "ksql-backup-" +
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss"));

    public static void main(String[] args) throws IOException {
        KSQLDB_SERVER = args[0];
        new File(BACKUP_DIR).mkdirs();
        JsonNode streamsResponse = backup("SHOW STREAMS;", "streams.json");

        JsonNode streams = streamsResponse.get(0).get("streams");
        if (streams != null) {
            for (JsonNode stream : streams) {
                String streamName = stream.get("name").asText();
                backup("DESCRIBE " + streamName + ";", "stream_" + streamName + ".json");
                backup("DESCRIBE " + streamName + " EXTENDED;", "stream_" + streamName + "_extended.json");
            }
        }

        backup("SHOW TABLES;", "tables.json");
        backupQueries();
        System.out.printf("KSQL metadata backup done in folder: %s%n", BACKUP_DIR);
    }

    private static JsonNode backup(String statement, String filename) throws IOException {
        JsonNode response = sendKsqlStatement(statement);
        FileWriter writer = new FileWriter(BACKUP_DIR + "/" + filename);
        writer.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
        writer.close();
        return response;
    }

    private static void backupQueries() throws IOException {
        JsonNode response = sendKsqlStatement("SHOW QUERIES EXTENDED;");
        FileWriter allWriter = new FileWriter(BACKUP_DIR + "/queries.json");
        allWriter.write(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response));
        allWriter.close();

        JsonNode queries = response.get(0).get("queries");
        if (queries != null) {
            for (JsonNode query : queries) {
                String queryId = query.get("id").asText();
                String queryString = query.get("queryString").asText();

                FileWriter queryWriter = new FileWriter(BACKUP_DIR + "/query_" + queryId + ".sql");
                queryWriter.write(queryString);
                queryWriter.close();
            }
        }
    }

    private static JsonNode sendKsqlStatement(String statement) throws IOException {
        String jsonPayload = mapper.writeValueAsString(new KsqlRequest(statement));

        RequestBody body = RequestBody.create(
                jsonPayload,
                MediaType.parse("application/vnd.ksql.v1+json")
        );

        Request request = new Request.Builder()
                .url(KSQLDB_SERVER + "/ksql")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response: " + response);
            }
            return mapper.readTree(response.body().string());
        }
    }

    private static class KsqlRequest {
        public final String ksql;
        public final Map<String, Object> streamsProperties = new HashMap<>();

        public KsqlRequest(String ksql) {
            this.ksql = ksql;
        }
    }
}
