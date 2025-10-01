package preload.config;

import java.util.List;

public class DbConfig {
    private DbType dbType;
    private String jdbcUrl;
    private String username;
    private String password;
    private String preStatement;
    private String query;
    private String insertSql;
    private OptionConfig option;
    private String tableName;
    private List<ColumnConfig> columns;

    public DbType getDbType() { return dbType; }
    public String getJdbcUrl() { return jdbcUrl; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getPreStatement() { return preStatement; }
    public String getQuery() { return query; }
    public String getInsertSql() { return insertSql; }
    public List<ColumnConfig> getColumns() { return columns; }
    public OptionConfig getOption() { return option; }
    public String getTableName() { return tableName; }
}
