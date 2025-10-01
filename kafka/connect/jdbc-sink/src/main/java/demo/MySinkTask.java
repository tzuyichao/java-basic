package demo;

import org.apache.kafka.connect.errors.ConnectException;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;

import java.sql.*;
import java.util.Collection;
import java.util.Map;

public class MySinkTask extends SinkTask {
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
        } catch (SQLException e) {
            throw new ConnectException("Failed to connect to database", e);
        }
    }

    private String buildSQL(SinkRecord record) {
        return "";
    }

    @Override
    public void put(Collection<SinkRecord> records) {
        for (SinkRecord record : records) {
            String sql = buildSQL(record);
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
