package converter;

import config.PropUtils;
import io.confluent.connect.jdbc.dialect.DatabaseDialect;
import io.confluent.connect.jdbc.dialect.DatabaseDialects;
import io.confluent.connect.jdbc.source.JdbcSourceConnectorConfig;
import io.confluent.connect.jdbc.source.SchemaMapping;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.json.JsonConverter;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.storage.ConverterConfig;
import org.apache.kafka.connect.storage.ConverterType;
import org.apache.kafka.connect.transforms.ValueToKey;

import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static config.KakfaConnectCore.*;

public class JsonConverterLab {
    static final String topic = "test.testopic.v0";

    public static void main(String[] args) {
        Map<String, String> props = PropUtils.getDefaultProps();
        JdbcSourceConnectorConfig config = new JdbcSourceConnectorConfig(props);

        final String url = config.getString(JdbcSourceConnectorConfig.CONNECTION_URL_CONFIG);
        ValueToKey<SourceRecord> xform = new ValueToKey<>();

        try(DatabaseDialect databaseDialect = DatabaseDialects.findBestFor(url, config);
            Connection connection = databaseDialect.getConnection();
            JsonConverter keyConverter = new JsonConverter();) {
            keyConverter.configure(Collections.singletonMap(ConverterConfig.TYPE_CONFIG, ConverterType.KEY.getName()));

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
                System.out.println("Value ===> \n" + record);
                SourceRecord sourceRecord = new SourceRecord(Collections.EMPTY_MAP, Collections.EMPTY_MAP, topic, schema, record);
                xform.configure(Collections.singletonMap(TRANSFORMS_PRIMARY_KEY_FIELDS, "EmployeeID"));
                SourceRecord transformed = xform.apply(sourceRecord);
                System.out.println("Transformed ===> \n" + transformed);
                System.out.println("Key Converter ===>\n" + new String(keyConverter.fromConnectData(topic, transformed.keySchema(), transformed.key()), Charset.forName("UTF-8")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
