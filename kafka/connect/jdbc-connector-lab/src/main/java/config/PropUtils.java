package config;

import io.confluent.connect.jdbc.source.JdbcSourceConnectorConfig;

import java.util.HashMap;
import java.util.Map;

public final class PropUtils {
    public static Map<String, String> getDefaultProps() {
        Map<String, String> props = new HashMap<>();
        props.put(JdbcSourceConnectorConfig.CONNECTION_URL_CONFIG, "jdbc:sqlserver://twtpesqlqa2:1433;databaseName=MDM_CDC");
        props.put(JdbcSourceConnectorConfig.CONNECTION_USER_CONFIG, "datagov");
        props.put(JdbcSourceConnectorConfig.CONNECTION_PASSWORD_CONFIG, "datagov!");
        props.put(JdbcSourceConnectorConfig.TABLE_WHITELIST_CONFIG, "Employees");
        props.put(JdbcSourceConnectorConfig.MODE_CONFIG, JdbcSourceConnectorConfig.MODE_TIMESTAMP);
        props.put(JdbcSourceConnectorConfig.TIMESTAMP_COLUMN_NAME_CONFIG, "modifyDate");
        props.put(JdbcSourceConnectorConfig.TOPIC_PREFIX_CONFIG, "prefix");
        return props;
    }
}
