package dialect;

import config.PropUtils;
import io.confluent.connect.jdbc.dialect.DatabaseDialect;
import io.confluent.connect.jdbc.dialect.DatabaseDialects;
import io.confluent.connect.jdbc.source.JdbcSourceConnectorConfig;
import io.confluent.connect.jdbc.source.SchemaMapping;
import org.apache.kafka.connect.data.Schema;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Map;

public class SchemaMappingLab {
    public static void main(String[] args) {
        Map<String, String> props = PropUtils.getDefaultProps();
        JdbcSourceConnectorConfig config = new JdbcSourceConnectorConfig(props);

        final String url = config.getString(JdbcSourceConnectorConfig.CONNECTION_URL_CONFIG);

        try(DatabaseDialect databaseDialect = DatabaseDialects.findBestFor(url, config);
            Connection connection = databaseDialect.getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Employees");
            ResultSetMetaData rsmd = rs.getMetaData();
            SchemaMapping schemaMapping = SchemaMapping.create("EmployeesSchema", rsmd,databaseDialect);
            Schema schema = schemaMapping.schema();
            System.out.println(schema);
            schema.fields().forEach(field -> {
                System.out.println(field.toString() + "|" + field.name() + "|" + field.schema().name() + "|" + field.schema().type() + "|" + field.schema().version() + "|" + field.schema().isOptional());
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
