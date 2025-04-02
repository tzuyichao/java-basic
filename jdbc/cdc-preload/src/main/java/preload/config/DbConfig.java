package preload.config;

import java.util.List;

public class DbConfig {
    private DbType dbType;
    private String jdbcUrl;
    private String username;
    private String password;
    private String query;
    private String insertSql;
    private List<ColumnConfig> columns;

    public DbType getDbType() { return dbType; }
    public String getJdbcUrl() { return jdbcUrl; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getQuery() { return query; }
    public String getInsertSql() { return insertSql; }
    public List<ColumnConfig> getColumns() { return columns; }
}
