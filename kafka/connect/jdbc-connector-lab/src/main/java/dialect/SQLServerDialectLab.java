package dialect;

import config.PropUtils;
import io.confluent.connect.jdbc.dialect.DatabaseDialect;
import io.confluent.connect.jdbc.dialect.DatabaseDialects;
import io.confluent.connect.jdbc.source.JdbcSourceConnectorConfig;
import io.confluent.connect.jdbc.util.TableDefinition;
import io.confluent.connect.jdbc.util.TableId;

import java.util.List;
import java.util.Map;

public class SQLServerDialectLab {
    public static void main(String[] args) {
        Map<String, String> props = PropUtils.getDefaultProps();
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
