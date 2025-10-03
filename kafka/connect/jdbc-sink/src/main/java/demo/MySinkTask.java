package demo;

import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class MySinkTask extends SinkTask {
    private static final Logger log = LoggerFactory.getLogger(MySinkTask.class);
    private Connection connection;
    private String tableName;

    @Override
    public void start(Map<String, String> props) {
        String url = props.get(MySinkConnector.CONNECTION_URL_CONFIG);
        String user = props.get(MySinkConnector.CONNECTION_USER_CONFIG);
        String password = props.get(MySinkConnector.CONNECTION_PASSWORD_CONFIG);
        this.tableName = props.get(MySinkConnector.TABLE_NAME_CONFIG);

        try {
            connection = DriverManager.getConnection(url, user, password);
            log.info("Connected to database");
        } catch (SQLException e) {
            throw new ConnectException("Failed to connect to database", e);
        }
    }

    static String escapeSql(String s) {
        if (s == null) return null;
        return s.replace("'", "''");
    }
    static String sqlLiteral(String s) {
        if (s == null) return "NULL";
        return "'" + escapeSql(s) + "'";
    }

    static String quoteTable(String table) {
        String[] parts = table.split("\\.");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parts.length; i++) {
            if (i > 0) sb.append('.');
            sb.append('[').append(parts[i]).append(']');
        }
        return sb.toString();
    }

    private String buildSQL(SinkRecord record) {
        if (record.value() instanceof Struct) {
            Struct envelope = (Struct) record.value();
            Struct payload = envelope.getStruct("payload");
            String deptCode = (String) payload.get("DeptCode");
            String name = (String) payload.get("Name");
            String mergeTemplate = "MERGE %s AS T USING (VALUES (%s, %s)) AS S(DeptCode, Name) ON T.DeptCode = S.DeptCode WHEN MATCHED AND ISNULL(T.Name, '') <> ISNULL(S.Name, '') THEN UPDATE SET T.Name = S.Name WHEN NOT MATCHED THEN INSERT (DeptCode, Name) VALUES (S.DeptCode, S.Name);";
            return String.format(mergeTemplate, tableName, sqlLiteral(deptCode), sqlLiteral(name));
        } else if (record.value() instanceof HashMap) {
            HashMap envelope = (HashMap) record.value();
            HashMap payload = (HashMap) envelope.get("payload");
            String deptCode = (String) payload.get("DeptCode");
            String name = (String) payload.get("Name");
            String mergeTemplate = "MERGE %s AS T USING (VALUES (%s, %s)) AS S(DeptCode, Name) ON T.DeptCode = S.DeptCode WHEN MATCHED AND ISNULL(T.Name, '') <> ISNULL(S.Name, '') THEN UPDATE SET T.Name = S.Name WHEN NOT MATCHED THEN INSERT (DeptCode, Name) VALUES (S.DeptCode, S.Name);";
            return String.format(mergeTemplate, tableName, sqlLiteral(deptCode), sqlLiteral(name));
        } else {
            throw new ConnectException("Unsupported value type: " + record.value().getClass());
        }
    }

    @Override
    public void put(Collection<SinkRecord> records) {
        for (SinkRecord record : records) {
            if (record.value() == null) continue;

            Map<String, Object> root;
            log.info("record: {}", record);
            String sql = buildSQL(record);
            log.info("sql: {}", sql);
            try (Statement stmt = connection.createStatement();) {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                throw new ConnectException("Failed to insert record", e);
            }
        }
    }

    @Override
    public void stop() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException ignore) { }
        }
    }

    @Override
    public String version() {
        return "1.0.0";
    }
}
