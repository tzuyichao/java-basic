package dialect;

import io.confluent.connect.jdbc.dialect.DatabaseDialect;
import io.confluent.connect.jdbc.dialect.DatabaseDialects;
import io.confluent.connect.jdbc.source.JdbcSourceConnectorConfig;
import io.confluent.connect.jdbc.util.TableDefinition;
import io.confluent.connect.jdbc.util.TableId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SQLServerDialectLab {
    public static void main(String[] args) {
        Map<String, String> props = new HashMap<>();
        props.put(JdbcSourceConnectorConfig.CONNECTION_URL_CONFIG, "jdbc:sqlserver://twtpesqlqa2:1433;databaseName=MDM_CDC");
        props.put(JdbcSourceConnectorConfig.CONNECTION_USER_CONFIG, "datagov");
        props.put(JdbcSourceConnectorConfig.CONNECTION_PASSWORD_CONFIG, "datagov!");
        props.put(JdbcSourceConnectorConfig.TABLE_WHITELIST_CONFIG, "Employees");
        props.put(JdbcSourceConnectorConfig.MODE_CONFIG, JdbcSourceConnectorConfig.MODE_TIMESTAMP);
        props.put(JdbcSourceConnectorConfig.TIMESTAMP_COLUMN_NAME_CONFIG, "modifyDate");
        props.put(JdbcSourceConnectorConfig.TOPIC_PREFIX_CONFIG, "prefix");
        JdbcSourceConnectorConfig config = new JdbcSourceConnectorConfig(props);

        final String url = config.getString(JdbcSourceConnectorConfig.CONNECTION_URL_CONFIG);

        try(DatabaseDialect databaseDialect = DatabaseDialects.findBestFor(url, config);) {
            System.out.println(databaseDialect.toString());

            List<TableId> tableIds = databaseDialect.tableIds(databaseDialect.getConnection());
            TableDefinition tableDefinition = databaseDialect.describeTable(databaseDialect.getConnection(), tableIds.get(0));
            System.out.println(tableDefinition);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
