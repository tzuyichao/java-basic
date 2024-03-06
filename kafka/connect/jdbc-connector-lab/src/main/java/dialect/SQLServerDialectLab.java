package dialect;

import io.confluent.connect.jdbc.dialect.DatabaseDialect;
import io.confluent.connect.jdbc.dialect.DatabaseDialects;
import io.confluent.connect.jdbc.source.JdbcSourceConnectorConfig;
import io.confluent.connect.jdbc.source.JdbcSourceTaskConfig;

import java.util.HashMap;
import java.util.Map;

public class SQLServerDialectLab {
    public static void main(String[] args) {
        Map<String, String> props = new HashMap<>();
        JdbcSourceTaskConfig config = new JdbcSourceTaskConfig(props);

        final String url = config.getString(JdbcSourceConnectorConfig.CONNECTION_URL_CONFIG);

        try(DatabaseDialect databaseDialect = DatabaseDialects.findBestFor(url, config);) {
            System.out.println(databaseDialect.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
