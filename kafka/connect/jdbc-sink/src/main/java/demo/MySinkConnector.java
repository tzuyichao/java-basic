package demo;

import org.apache.kafka.common.config.ConfigDef;
import org.apache.kafka.connect.connector.Task;
import org.apache.kafka.connect.sink.SinkConnector;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class MySinkConnector extends SinkConnector {
    public static final String CONNECTION_URL_CONFIG = "connection.url";
    public static final String CONNECTION_USER_CONFIG = "connection.user";
    public static final String CONNECTION_PASSWORD_CONFIG = "connection.password";
    public static final String TABLE_NAME_CONFIG = "table.name";

    private static final ConfigDef CONFIG_DEF = new ConfigDef()
            .define(CONNECTION_URL_CONFIG, ConfigDef.Type.STRING, ConfigDef.Importance.HIGH,
                    "JDBC connection URL")
            .define(CONNECTION_USER_CONFIG, ConfigDef.Type.STRING, ConfigDef.Importance.HIGH,
                    "DB user")
            .define(CONNECTION_PASSWORD_CONFIG, ConfigDef.Type.PASSWORD, ConfigDef.Importance.HIGH,
                    "DB password")
            .define(TABLE_NAME_CONFIG, ConfigDef.Type.STRING, ConfigDef.Importance.HIGH,
                    "Target table name");

    private Map<String, String> props;

    @Override
    public void start(Map<String, String> props) {
        this.props = props;
    }

    @Override
    public Class<? extends Task> taskClass() {
        return MySinkTask.class;
    }

    @Override
    public List<Map<String, String>> taskConfigs(int maxTasks) {
        return Collections.nCopies(maxTasks, props);
    }

    @Override
    public void stop() { }

    @Override
    public ConfigDef config() {
        return CONFIG_DEF;
    }

    @Override
    public String version() {
        return "1.0.0";
    }
}
