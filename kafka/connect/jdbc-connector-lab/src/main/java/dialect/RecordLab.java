package dialect;

import config.PropUtils;
import io.confluent.connect.jdbc.dialect.DatabaseDialect;
import io.confluent.connect.jdbc.dialect.DatabaseDialects;
import io.confluent.connect.jdbc.source.JdbcSourceConnectorConfig;
import io.confluent.connect.jdbc.source.SchemaMapping;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import static config.KakfaConnectCore.METHOD_FIELDSETTER_SETFIELD;
import static config.KakfaConnectCore.METHOD_SCHEMAMAPPING_FIELDSETTERS;

public class RecordLab {
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
            Method fieldSettersMethod = SchemaMapping.class.getDeclaredMethod(METHOD_SCHEMAMAPPING_FIELDSETTERS);
            fieldSettersMethod.setAccessible(true);
            Method setFieldMethod = SchemaMapping.FieldSetter.class.getDeclaredMethod(METHOD_FIELDSETTER_SETFIELD, Struct.class, ResultSet.class);
            setFieldMethod.setAccessible(true);
            while(rs.next()) {
                Struct record = new Struct(schema);
                List<SchemaMapping.FieldSetter> fieldSetters = (List<SchemaMapping.FieldSetter>) fieldSettersMethod.invoke(schemaMapping);
                for(SchemaMapping.FieldSetter setter : fieldSetters) {
                    setFieldMethod.invoke(setter, record, rs);
                }
                System.out.println(record);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
